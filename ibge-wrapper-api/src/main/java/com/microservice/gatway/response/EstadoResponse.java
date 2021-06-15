package com.microservice.gatway.response;

import com.microservice.gatway.model.Estado;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EstadoResponse {

    private List<Estado> result;
}
