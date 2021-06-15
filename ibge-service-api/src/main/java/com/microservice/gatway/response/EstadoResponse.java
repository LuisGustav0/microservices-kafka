package com.microservice.gatway.response;

import com.microservice.gatway.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponse {

    List<Estado> result;
}
