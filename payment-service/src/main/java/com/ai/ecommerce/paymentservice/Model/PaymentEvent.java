package com.ai.ecommerce.paymentservice.Model;

public class PaymentEvent {
    private String paymentId;
    private Long orderId;      // may be null if not provided
    private String userId;
    private String status;     // SUCCESS / FAILED
    private double amount;     // mocked amount

    public PaymentEvent() {}

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
