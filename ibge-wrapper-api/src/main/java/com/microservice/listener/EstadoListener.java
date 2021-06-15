package com.microservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.gatway.model.Estado;
import com.microservice.gatway.response.EstadoResponse;
import com.microservice.service.SearchEstadoService;
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
public class EstadoListener {

    private final SearchEstadoService estadoService;

    private final ObjectMapper mapper;

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    public Message<String> execute(@Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
                                   @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws JsonProcessingException {

        TimeUtil timeUtil = new TimeUtil();

        List<Estado> listEstado = this.estadoService.execute();

        String jsonReturn = mapper.writeValueAsString(EstadoResponse.builder().result(listEstado).build());

        timeUtil.showLog("EstadoListener.execute ");

        return MessageBuilder.withPayload(jsonReturn)
                             .setHeader(KafkaHeaders.TOPIC, replyTo)
                             .setHeader(KafkaHeaders.CORRELATION_ID, correlation)
                             .build();
    }
}
