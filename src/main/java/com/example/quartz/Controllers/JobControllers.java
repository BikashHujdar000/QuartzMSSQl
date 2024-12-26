package com.example.quartz.Controllers;

import com.example.quartz.Models.EmailRequest;
import com.example.quartz.Response.JobResponse;
import com.example.quartz.Services.ScheduleBuilder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@Slf4j
public class JobControllers {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleBuilder scheduleBuilder;


    @GetMapping("/get")
    public ResponseEntity<String> getString() {
        log.info("::::::::::::::Testing is working :::::::");
        return ResponseEntity.ok("Application is running");

    }

    @PostMapping("/schedule/email")
    public ResponseEntity<JobResponse> scheduleEmail(@Valid  @RequestBody  EmailRequest emailRequest ) {

        try {


            ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDateTime(), emailRequest.getTimeZone());

            JobDetail jobDetail = this.scheduleBuilder.buildJobDetail(emailRequest);
            log.info(":::::::::::::JObId :::::::::;;"+jobDetail.getKey().getName());

            Trigger trigger = this.scheduleBuilder.buildTrigger(jobDetail, dateTime);
            log.info("::::::::::::triggerTime :::::::::::::"+trigger.getStartTime());

            scheduler.scheduleJob(jobDetail, trigger);

            //generating the response
            JobResponse emailResponse = new JobResponse(true, jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Schedule Successfully");
            return new ResponseEntity<>(emailResponse, HttpStatus.CREATED);

        } catch (SchedulerException e) {
            log.error("::::::::::::::::Failed to schedule job:::::::::::::::: ", e);

            JobResponse emailResponse = new JobResponse(false, "Error while scheduling ,please try again");
            return new ResponseEntity<>(emailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
