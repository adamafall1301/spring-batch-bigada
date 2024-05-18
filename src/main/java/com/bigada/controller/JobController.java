package com.bigada.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/api/job")
@RestController
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;


    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws Exception {

        HashMap<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

        JobParameters jobParameters = new JobParameters(params);

        if (jobName.equals(firstJob.getName())) {
            jobLauncher.run(firstJob, jobParameters);
        } else {
            jobLauncher.run(secondJob, jobParameters);
        }

        return "Job Started ...";
    }
}
