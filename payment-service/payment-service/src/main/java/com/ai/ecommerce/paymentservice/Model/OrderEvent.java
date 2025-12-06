package com.ai.ecommerce.paymentservice.Model;

import java.util.List;

public class OrderEvent {
    private Long orderId;            // optional — if order producer sends it
    private String userId;           // could be numeric string or string id
    private List<OrderItem> orderItems;

    public OrderEvent() {}

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public static class OrderItem {
        private String productId;
        private int quantity;

        public OrderItem() {}
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
