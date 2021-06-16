package com.microservice.gatway.response;

import com.microservice.gatway.model.Cidade;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CidadeResponse {

    private List<Cidade> result;
}
