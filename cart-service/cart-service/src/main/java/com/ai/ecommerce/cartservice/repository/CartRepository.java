package com.ai.ecommerce.cartservice.repository;

import com.ai.ecommerce.cartservice.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    private static final String CART_KEY = "CART_";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Cart saveCart(Cart cart) {
        redisTemplate.opsForValue().set(CART_KEY + cart.getUserId(), cart);
        return cart;
    }

    public Cart getCart(String userId) {
        Object cart = redisTemplate.opsForValue().get(CART_KEY + userId);
        return cart != null ? (Cart) cart : null;
    }

    public void deleteCart(String userId) {
        redisTemplate.delete(CART_KEY + userId);
    }
}
