package com.alba.kafka_microservices.service;

import com.alba.kafka_microservices.dto.CreatedProductDto;
import core.core.ProductCreatedEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService{

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreatedProductDto createdProductDto) {

        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,createdProductDto.getTittle(),
                createdProductDto.getPrice(),createdProductDto.getQuantity());

        ProducerRecord<String, ProductCreatedEvent> record = new ProducerRecord<>(
                "product-createdevents-topic",
                productId,
                productCreatedEvent
        );

        record.headers().add("messageId", UUID.randomUUID().toString().getBytes());

       CompletableFuture<SendResult<String,ProductCreatedEvent>> future =
               kafkaTemplate.send(record);

       future.whenComplete((result,exception)->{
           if (exception != null){
               LOGGER.error("Failed to send message {}", exception.getMessage());
           }else{
                LOGGER.info("Message sent succesfully {}",result.getRecordMetadata());
           }
        });

       LOGGER.info("return {}",productId);
       return productId;
    }
}
