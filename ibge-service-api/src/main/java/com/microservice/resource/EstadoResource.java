package com.microservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.resource.response.CidadeResponse;
import com.microservice.resource.response.EstadoResponse;
import com.microservice.service.estado.SearchCidadeByUfService;
import com.microservice.service.estado.SearchEstadoService;
import com.microservice.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoResource {

    private final SearchEstadoService estadoService;

    private final SearchCidadeByUfService cidadeService;

    @GetMapping
    public EstadoResponse search() throws InterruptedException, ExecutionException, JsonProcessingException {
        log.info("EstadoResource.search - Initializer search");

        var timeUtil = new TimeUtil();

        EstadoResponse response = this.estadoService.execute();

        timeUtil.showLog("EstadoResource.search");

        return response;
    }

    @GetMapping("/{uf}/cidades")
    public CidadeResponse search(@PathVariable String uf) throws InterruptedException, ExecutionException, JsonProcessingException {
        log.info("EstadoResource.search - Initializer search by uf: " + uf);

        var timeUtil = new TimeUtil();

        CidadeResponse response = this.cidadeService.execute(uf);

        timeUtil.showLog("EstadoResource.search");

        return response;
    }
}
