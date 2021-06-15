package com.microservice.gatway.feign;

import com.microservice.gatway.model.Estado;
import feign.RequestLine;

import java.util.List;

public interface EstadoClient {

    @RequestLine("GET /api/v1/localidades/estados")
    List<Estado> get();
}
