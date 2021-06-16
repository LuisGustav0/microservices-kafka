package com.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@EnableKafka
@EnableCaching
@SpringBootApplication
public class IbgeServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbgeServiceApiApplication.class, args);
    }
}
