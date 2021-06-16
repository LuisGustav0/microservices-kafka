package com.microservice.service.estado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.gatway.model.EstadoRequestTopic;
import com.microservice.resource.response.CidadeResponse;
import com.microservice.resource.response.EstadoResponse;
import com.microservice.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchCidadeByUfService {

    private final ReplyingKafkaTemplate kafkaTemplate;

    private final ObjectMapper mapper;

    @Value("${kafka.topic.request-topic-cidade}")
    private String requestTopic;

    @Value("${kafka.topic.requestreply-topic-cidade}")
    private String requestReplyTopic;

    private ProducerRecord<String, String> getProducerRecord(String uf) throws JsonProcessingException {
        log.info("SearchCidadeByUfService.getProducerRecord - Convert json object");
        var strJson = this.mapper.writeValueAsString(EstadoRequestTopic.builder().uf(uf).build());

        log.info("SearchEstadoService.getProducerRecord - Assembling record producer to send kafka");
        var producerRecord = new ProducerRecord(requestTopic, strJson);
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        return producerRecord;
    }

    private void imprimirHeader(Header header) {
        log.info(header.key() + ":" + Arrays.toString(header.value()));
    }

    private CidadeResponse getCidadeResponse(RequestReplyFuture<String, String, String> sendAndReceive) throws JsonProcessingException, ExecutionException, InterruptedException {
        log.info("SearchCidadeByUfService.getCidadeResponse - Receiving feedback");

        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
        sendResult.getProducerRecord().headers().forEach(this::imprimirHeader);

        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();

        return this.mapper.readValue(consumerRecord.value(), CidadeResponse.class);
    }

    public CidadeResponse execute(String uf) throws JsonProcessingException, ExecutionException, InterruptedException {
        log.info("SearchCidadeByUfService.execute - Initializer execute");

        var timeUtil = new TimeUtil();

        var producerRecord = this.getProducerRecord(uf);

        log.info("SearchEstadoService.execute - Send kafka");
        RequestReplyFuture<String, String, String> sendAndReceive = this.kafkaTemplate.sendAndReceive(producerRecord);

        CidadeResponse response = this.getCidadeResponse(sendAndReceive);

        timeUtil.showLog("SearchEstadoService.execute retorno kafka");

        return response;
    }
}
