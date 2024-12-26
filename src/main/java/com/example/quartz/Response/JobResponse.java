package com.example.quartz.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {

    private boolean success;

    private String jobId;

    private String jobGroup;

    //uniques jobId and jobGroup
    // we can have muliple jobId in a single jobGroup

    private String message;

    public JobResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public JobResponse(boolean success, String jobId, String jobGroup, String message) {
        this.success = success;
        this.jobId = jobId;
        this.jobGroup = jobGroup;
        this.message = message;

    }

}
