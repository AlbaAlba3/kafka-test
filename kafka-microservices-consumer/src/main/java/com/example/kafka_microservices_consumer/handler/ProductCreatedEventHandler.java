package com.example.kafka_microservices_consumer.handler;


import com.example.kafka_microservices_consumer.entity.ProcessEventEntity;
import com.example.kafka_microservices_consumer.exception.NonRetryableException;
import com.example.kafka_microservices_consumer.exception.RetryableException;
import com.example.kafka_microservices_consumer.repository.ProcessEventRepository;
import core.core.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@KafkaListener(topics = "product-createdevents-topic")
public class ProductCreatedEventHandler {

    private RestTemplate restTemplate;
    private ProcessEventRepository processEventRepository;

    public ProductCreatedEventHandler(RestTemplate restTemplate, ProcessEventRepository processEventRepository) {
        this.restTemplate = restTemplate;
        this.processEventRepository = processEventRepository;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());



    @KafkaHandler
    @Transactional
    public void handle(@Payload ProductCreatedEvent productCreatedEvent,
                       @Header("messageId") String messageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messgageKey){

        LOGGER.info("Recieved event {}", productCreatedEvent.getTittle());

        ProcessEventEntity processEventEntity = processEventRepository.findByMessageId(messageId);

        if (processEventEntity != null){
            LOGGER.info("Dublicate event {}", processEventEntity.getId());
            return;
        }

        try {
            String url = "http://localhost:8090/response/200 ";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().value() == HttpStatus.OK.value()){
                LOGGER.info("Recieved resposne {}", response.getBody());

            }

        }catch (ResourceAccessException e){
            LOGGER.error(e.getMessage());
            throw new RetryableException(e);
        }
        catch (HttpServerErrorException e){
            LOGGER.error(e.getMessage());
            throw new NonRetryableException(e);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new NonRetryableException(e);
        }

        try{
        processEventRepository.save(new ProcessEventEntity(messageId,productCreatedEvent.getProductId()));}
        catch (DataIntegrityViolationException e){
            throw new NonRetryableException("TEST EXCEPTION");
        }

    }

}
