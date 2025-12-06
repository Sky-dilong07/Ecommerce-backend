package com.ai.ecommerce.paymentservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ai.ecommerce.paymentservice.Model.OrderEvent;
import com.ai.ecommerce.paymentservice.Model.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderEventListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventListener.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PaymentEventProducer producer;

    public OrderEventListener(PaymentEventProducer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-service-group")
    public void onOrderCreated(String message) {

        logger.info("PaymentService received Kafka event: {}", message);

        OrderEvent orderEvent = null;

        try {
            orderEvent = objectMapper.readValue(message, OrderEvent.class);

        } catch (Exception jsonEx) {
            logger.warn("Failed to parse JSON. Trying fallback format...");

            try {
                Pattern p = Pattern.compile("Order\\s+Placed\\s+with\\s+ID:\\s*(\\d+)");
                Matcher m = p.matcher(message);

                if (m.find()) {
                    orderEvent = new OrderEvent();
                    orderEvent.setOrderId(Long.valueOf(m.group(1)));
                }
            } catch (Exception e) {
                logger.error("Message parsing failed completely: {}", e.getMessage(), e);
            }
        }

        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setPaymentId(UUID.randomUUID().toString());

        if (orderEvent != null) {
            paymentEvent.setOrderId(orderEvent.getOrderId());
            paymentEvent.setUserId(orderEvent.getUserId());
        }

        paymentEvent.setStatus("SUCCESS");
        paymentEvent.setAmount(0.0);

        producer.sendPaymentEvent(paymentEvent);
    }
}
