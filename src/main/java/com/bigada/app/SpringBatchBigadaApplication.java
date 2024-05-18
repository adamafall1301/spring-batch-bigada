package com.bigada.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.bigada.config", "com.bigada.service", "com.bigada.listener", "com.bigada.reader",
        "com.bigada.processor", "com.bigada.writer", "com.bigada.controller"})
public class SpringBatchBigadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchBigadaApplication.class, args);
    }

}
