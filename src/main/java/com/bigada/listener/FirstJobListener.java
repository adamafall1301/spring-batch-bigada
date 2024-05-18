package com.bigada.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("Before Job Exec: "+jobExecution.getJobInstance().getJobName());
        System.out.println("Job parameters: "+jobExecution.getJobParameters());
        System.out.println("Le context du Job: "+jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("jec", "jec value");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("After Job Exec: "+jobExecution.getJobInstance().getJobName());
        System.out.println("Job parameters: "+jobExecution.getJobParameters());
        System.out.println("Le context du Job: "+jobExecution.getExecutionContext());
    }
}
