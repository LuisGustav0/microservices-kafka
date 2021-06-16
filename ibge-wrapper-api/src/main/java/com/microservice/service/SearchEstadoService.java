package com.microservice.service;

import com.microservice.gatway.feign.EstadoClient;
import com.microservice.gatway.model.Estado;
import com.microservice.util.TimeUtil;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SearchEstadoService {

    @Value("${uri-servico-dados-ibge-gov-br}")
    private String uriServicoDadosIbgeGobBr;

    public List<Estado> execute() {
        var timeUtil = new TimeUtil();

        log.info("SearchEstadoService.execute - Initializer execute");

        var estadoClient = Feign.builder()
                                .decoder(new GsonDecoder())
                                .target(EstadoClient.class, uriServicoDadosIbgeGobBr);

        timeUtil.showLog("SearchEstadoService.execute");

        return estadoClient.get();
    }
}
