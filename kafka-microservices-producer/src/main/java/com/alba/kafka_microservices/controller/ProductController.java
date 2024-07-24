package com.alba.kafka_microservices.controller;

import com.alba.kafka_microservices.dto.CreatedProductDto;
import com.alba.kafka_microservices.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreatedProductDto createdProductDto){
       String  productId = productService.createProduct(createdProductDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
