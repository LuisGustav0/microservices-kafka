package com.microservice.service;

import com.microservice.gatway.feign.CidadeClient;
import com.microservice.gatway.model.Cidade;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class SearchCidadeByUfService {

    @Value("${uri-servico-dados-ibge-gov-br}")
    private String uriServicoDadosIbgeGobBr;

    public List<Cidade> execute(String uf) {
        var startTime = Instant.now();

        log.info("SearchCidadeByUfService.execute - Initializer execute");

        var cidadeClient = Feign.builder()
                                .decoder(new GsonDecoder())
                                .target(CidadeClient.class, uriServicoDadosIbgeGobBr);

        var stopTime = Instant.now();

        long time = Duration.between(startTime, stopTime).toMillis();

        log.info("SearchCidadeByUfService.execute - Tempo finish: " + time + " millis");

        return cidadeClient.get(uf);
    }
}
