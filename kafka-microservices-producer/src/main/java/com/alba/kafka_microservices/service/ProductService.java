package com.alba.kafka_microservices.service;

import com.alba.kafka_microservices.dto.CreatedProductDto;

public interface ProductService {

    String createProduct (CreatedProductDto createdProductDto);
}
