package com.microservice.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class TimeUtil {

    private Instant startTime;

    public TimeUtil() {
        this.startTime = Instant.now();
    }

    public void showLog(String message) {
        var stopTime = Instant.now();

        long time = Duration.between(startTime, stopTime).toMillis();

        log.info(message + " - Time finish: " + time + " millis");
    }
}
