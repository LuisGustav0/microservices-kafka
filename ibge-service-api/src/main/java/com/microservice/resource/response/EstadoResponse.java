package com.microservice.resource.response;

import com.microservice.gatway.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponse implements Serializable {

    List<Estado> result;
}
