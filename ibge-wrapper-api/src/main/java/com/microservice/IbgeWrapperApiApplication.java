package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IbgeWrapperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbgeWrapperApiApplication.class, args);
    }
}
