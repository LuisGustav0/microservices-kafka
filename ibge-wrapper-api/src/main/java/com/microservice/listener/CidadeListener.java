package com.microservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.gatway.model.Cidade;
import com.microservice.gatway.model.Estado;
import com.microservice.gatway.request.EstadoRequestTopic;
import com.microservice.gatway.response.CidadeResponse;
import com.microservice.service.SearchCidadeByUfService;
import com.microservice.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeListener {

    private final SearchCidadeByUfService cidadeService;

    private final ObjectMapper mapper;

    @KafkaListener(topics = "${kafka.topic.request-topic-cidade}")
    public Message<String> execute(String uf,
                                   @Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
                                   @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws JsonProcessingException {

        var timeUtil = new TimeUtil();

        EstadoRequestTopic request = mapper.readValue(uf, EstadoRequestTopic.class);

        List<Cidade> listCidade = this.cidadeService.execute(request.getUf());

        String jsonReturn = mapper.writeValueAsString(CidadeResponse.builder().result(listCidade).build());

        timeUtil.showLog("EstadoListener.execute");

        return MessageBuilder.withPayload(jsonReturn)
                             .setHeader(KafkaHeaders.TOPIC, replyTo)
                             .setHeader(KafkaHeaders.CORRELATION_ID, correlation)
                             .build();
    }
}
