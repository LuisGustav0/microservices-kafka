package com.microservice.resource.response;

import com.microservice.gatway.model.Cidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CidadeResponse {

    List<Cidade> result;
}
