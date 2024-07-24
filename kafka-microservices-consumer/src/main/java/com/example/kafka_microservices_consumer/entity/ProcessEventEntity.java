package com.example.kafka_microservices_consumer.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "process_events")
public class ProcessEventEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private String messageId;

    @Column(nullable = false)
    private String productId;

    public ProcessEventEntity() {
    }

    public ProcessEventEntity( String messageId, String productId) {
        this.messageId = messageId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
