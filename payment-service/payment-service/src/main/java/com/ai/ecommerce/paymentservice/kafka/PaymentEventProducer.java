package com.ai.ecommerce.paymentservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ai.ecommerce.paymentservice.Model.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentEventProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC = "payment-processed";

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentEvent(PaymentEvent event) {
        try {
            String msg = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, msg);

            logger.info("Payment event sent: {}", msg);

        } catch (JsonProcessingException e) {
            logger.error("Error serializing PaymentEvent: {}", e.getMessage(), e);
        }
    }
}
