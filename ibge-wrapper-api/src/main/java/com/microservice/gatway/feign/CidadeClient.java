package com.microservice.gatway.feign;

import com.microservice.gatway.model.Cidade;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface CidadeClient {

    @RequestLine("GET /api/v1/localidades/estados/{UF}/municipios")
    List<Cidade> get(@Param("UF") String uf);
}
