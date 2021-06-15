package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableKafka
@SpringBootApplication
public class IbgeServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbgeServiceApiApplication.class, args);
    }
}
