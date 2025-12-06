package com.ai.ecommerce.cartservice.controller;

import com.ai.ecommerce.cartservice.model.Cart;
import com.ai.ecommerce.cartservice.model.CartItem;
import com.ai.ecommerce.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add item to cart
    @PostMapping("/add")
    public Cart addItem(@RequestParam("userId") String userId, @RequestBody CartItem item) {
        return cartService.addItem(userId, item);
    }

    // Update item in cart
    @PutMapping("/update")
    public Cart updateItem(@RequestParam("userId") String userId, @RequestBody CartItem item) {
        return cartService.updateItem(userId, item);
    }

    // Remove item from cart
    @DeleteMapping("/remove/{userId}/{productId}")
    public Cart removeItem(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
        return cartService.removeItem(userId, productId);
    }

    // Get full cart
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable("userId") String userId) {
        return cartService.getCart(userId);
    }

    // Clear whole cart
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable("userId") String userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully!";
    }
}
