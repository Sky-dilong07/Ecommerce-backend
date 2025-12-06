package com.ai.ecommerce.cartservice.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String userId;
    private List<CartItem> items = new ArrayList<>();
    private double totalAmount;

    public Cart() {}

    public Cart(String userId, List<CartItem> items, double totalAmount) {
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
