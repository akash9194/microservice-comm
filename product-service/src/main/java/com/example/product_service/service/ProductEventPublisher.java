package com.example.product_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public ProductEventPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${product.topic.name}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(String message) {
        kafkaTemplate.send(topic, message);
    }
}

