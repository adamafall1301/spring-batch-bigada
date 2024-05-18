package com.bigada.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;


@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Le nom du step: "+stepExecution.getStepName());
        System.out.println("Job parameters: "+stepExecution.getJobParameters());
        System.out.println("Le context du Job: "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Le context du step: "+stepExecution.getExecutionContext());
        stepExecution.getExecutionContext().put("sec", "sec value");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Le nom du step: "+stepExecution.getStepName());
        System.out.println("Job parameters: "+stepExecution.getJobParameters());
        System.out.println("Le context du Job: "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Le context du step: "+stepExecution.getExecutionContext());

        return null;
    }
}
