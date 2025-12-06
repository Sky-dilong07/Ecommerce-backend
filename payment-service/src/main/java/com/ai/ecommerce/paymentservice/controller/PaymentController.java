package com.ai.ecommerce.paymentservice.controller;

import com.ai.ecommerce.paymentservice.kafka.PaymentEventProducer;
import com.ai.ecommerce.paymentservice.Model.PaymentEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentEventProducer producer;

    public PaymentController(PaymentEventProducer producer) {
        this.producer = producer;
    }

    // Mock endpoint to create a payment and publish event
    @PostMapping("/charge")
    public ResponseEntity<PaymentEvent> charge(@RequestParam(required = false) Long orderId,
                                               @RequestParam(required = false) String userId,
                                               @RequestParam(required = false, defaultValue = "0") double amount) {
        PaymentEvent pe = new PaymentEvent();
        pe.setPaymentId(UUID.randomUUID().toString());
        pe.setOrderId(orderId);
        pe.setUserId(userId);
        pe.setStatus("SUCCESS");
        pe.setAmount(amount);

        producer.sendPaymentEvent(pe);
        return ResponseEntity.ok(pe);
    }
}
