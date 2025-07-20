package com.example.notification_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {
    @KafkaListener(topics = "${product.topic.name}", groupId = "notification-group")
    public void listen(String message) {
        System.out.println("📬 Received event: " + message);
        // You can process or send notifications here
    }
}
