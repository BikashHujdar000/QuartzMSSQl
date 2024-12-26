package com.example.quartz.Job;

import com.example.quartz.Services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Slf4j
public class EmailJob  extends QuartzJobBean {

    @Autowired
    private EmailService emailService;


    @Autowired
    private MailProperties mailProperties;



    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {



        JobDataMap jobDataMap =  context.getMergedJobDataMap();
        String recipientEmail = jobDataMap.getString("email");
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");

        LocalDateTime emailTime = LocalDateTime.now();
        log.info("Sending email to  '{}' at: {}", recipientEmail, emailTime);

        // sending the job to perform
        emailService.sendMail(mailProperties.getUsername(), recipientEmail, subject, body);

    }
}
