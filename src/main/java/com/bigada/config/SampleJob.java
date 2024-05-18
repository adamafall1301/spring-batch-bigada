package com.bigada.config;

import com.bigada.listener.FirstJobListener;
import com.bigada.listener.FirstStepListener;
import com.bigada.processor.FirstItemProcessor;
import com.bigada.reader.FirstItemReader;
import com.bigada.service.SecondTasklet;
import com.bigada.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired()
    private SecondTasklet secondTasklet;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;

    @Bean
    public Job firstJob() throws Exception {
        return jobBuilderFactory.get("FirstJob")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }

    @Bean
    protected Step firstStep() {
        return stepBuilderFactory.get("FirstStep")
                .tasklet(firstTask())
                .listener(firstStepListener)
                .build();
    }

    @Bean
    protected Step secondStep() {
        return stepBuilderFactory.get("SecondStep")
                .tasklet(secondTasklet)
                .build();
    }

    @Bean
    protected Tasklet firstTask() {
        return (contribution, chunkContext) -> {
            System.out.println("This is first tasklet step");
            System.out.println("Context du Step: " + chunkContext.getStepContext());
            return RepeatStatus.FINISHED;
        };
    }

    /**
     * @Bean protected Tasklet secondTask() {
     * return new Tasklet() {
     * @Override public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
     * System.out.println("This is the second tasklet step");
     * return RepeatStatus.FINISHED;
     * }
     * };
     * }
     */

    @Bean
    public Job secondJob() throws Exception {
        return jobBuilderFactory.get("SecondJob")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(secondStep())
                .build();
    }

    @Bean
    protected Step firstChunkStep() {
        return stepBuilderFactory.get("firstChunkStep")
                .<Integer, Long>chunk(4)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
