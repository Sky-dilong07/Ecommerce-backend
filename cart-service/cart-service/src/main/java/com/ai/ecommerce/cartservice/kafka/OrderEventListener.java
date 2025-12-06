package com.ai.ecommerce.cartservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class OrderEventListener {

    private final RedisTemplate<String, Object> redisTemplate;

    public OrderEventListener(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @KafkaListener(topics = "order-created", groupId = "cart-service-group")
    public void consumeOrderEvent(String message) {
        System.out.println("Received order event: " + message);

        // Extract userId from message (for now, parse simple string)
        // Example message: "Order Placed with ID: 5 for userId: 123"
        String userId = extractUserId(message); // implement parsing logic
        if (userId != null) {
            redisTemplate.delete("cart:" + userId);
            System.out.println("Cart cleared for user: " + userId);
        }
    }

    private String extractUserId(String message) {
        // Simple parsing logic (if message includes userId)
        // TODO: Adjust according to actual message format
        return "123"; // placeholder
    }
}
