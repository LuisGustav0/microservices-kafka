package com.microservice.resource.response;

import com.microservice.gatway.model.Estado;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EstadoResponse {

    List<Estado> result;
}
