package com.microservice.resource.response;

import com.microservice.gatway.model.Cidade;
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
public class CidadeResponse implements Serializable {

    List<Cidade> result;
}
