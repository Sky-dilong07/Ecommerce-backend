package ai.ecommerce.orderservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC = "order-created";

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(Long orderId, Long userId, Double totalAmount) {
        try {
            OrderEvent event = new OrderEvent();
            event.setOrderId(orderId);
            event.setUserId(userId);
            event.setTotalAmount(totalAmount);

            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);

            logger.info("Order event sent to Kafka: {}", message);

        } catch (Exception e) {
            logger.error("Error sending order event to Kafka: {}", e.getMessage(), e);
        }
    }
}
