package ai.ecommerce.orderservice.dto;

import java.util.List;

public class OrderRequest {

    private Long userId;
    private List<OrderItemRequest> items;

    public OrderRequest() {}

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
