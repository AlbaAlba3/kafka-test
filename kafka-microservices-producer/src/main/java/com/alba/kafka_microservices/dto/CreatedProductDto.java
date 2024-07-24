package com.alba.kafka_microservices.dto;

import java.math.BigDecimal;

public class CreatedProductDto {

    private String tittle;
    private BigDecimal price;
    private Integer quantity;

    public CreatedProductDto() {
    }

    public CreatedProductDto(String tittle, BigDecimal price, Integer quantity) {
        this.tittle = tittle;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
