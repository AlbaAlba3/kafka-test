package com.example.kafka_microservices_consumer.repository;

import com.example.kafka_microservices_consumer.entity.ProcessEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessEventRepository extends JpaRepository<ProcessEventEntity, Long> {

        ProcessEventEntity findByMessageId(String messageId);
}
