package com.ai.ecommerce.notificationservice.kafka;

import com.ai.ecommerce.notificationservice.model.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymenteventListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymenteventListener.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "payment-processed", groupId = "notification-service-group")
    public void consume(String message) {
        try {
            logger.info("NotificationService received event: {}", message);

            PaymentEvent event = objectMapper.readValue(message, PaymentEvent.class);

            logger.info("Sending notification → User: {}, Order: {}, Amount: {}, Status: {}",
                    event.getUserId(),
                    event.getOrderId(),
                    event.getAmount(),
                    event.getStatus()
            );

        } catch (Exception e) {
            logger.error("Error in NotificationService while consuming message: {}", e.getMessage(), e);
        }
    }
}
