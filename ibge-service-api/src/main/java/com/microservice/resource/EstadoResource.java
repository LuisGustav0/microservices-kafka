package com.microservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.gatway.response.EstadoResponse;
import com.microservice.service.estado.SearchEstadoService;
import com.microservice.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoResource {

    private final SearchEstadoService estadoService;

    @GetMapping
    public EstadoResponse search() throws InterruptedException, ExecutionException, JsonProcessingException {
        log.info("EstadoResource.search - Initializer search");

        TimeUtil timeUtil = new TimeUtil();

        EstadoResponse response = this.estadoService.execute();

        timeUtil.showLog("EstadoResource.search ");

        return response;
    }
}
