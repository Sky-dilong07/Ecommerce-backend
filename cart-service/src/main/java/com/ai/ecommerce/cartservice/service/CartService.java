package com.ai.ecommerce.cartservice.service;

import com.ai.ecommerce.cartservice.model.Cart;
import com.ai.ecommerce.cartservice.model.CartItem;
import com.ai.ecommerce.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add item to cart
    public Cart addItem(String userId, CartItem item) {
        Cart cart = cartRepository.getCart(userId);

        // if cart doesn't exist create new
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }

        boolean productExists = false;

        // If product exists -> increase quantity
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                productExists = true;
                break;
            }
        }

        // If product does not exist -> add new item
        if (!productExists) {
            cart.getItems().add(item);
        }

        updateTotal(cart);
        return cartRepository.saveCart(cart);
    }

    // Update item quantity
    public Cart updateItem(String userId, CartItem item) {
        Cart cart = cartRepository.getCart(userId);
        if (cart == null) return null;

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.setQuantity(item.getQuantity());
                break;
            }
        }

        updateTotal(cart);
        return cartRepository.saveCart(cart);
    }

    // Remove item
    public Cart removeItem(String userId, String productId) {
        Cart cart = cartRepository.getCart(userId);
        if (cart == null) return null;

        cart.getItems().removeIf(cartItem -> cartItem.getProductId().equals(productId));

        updateTotal(cart);
        return cartRepository.saveCart(cart);
    }

    // Get full cart
    public Cart getCart(String userId) {
        return cartRepository.getCart(userId);
    }

    // Clear full cart
    public void clearCart(String userId) {
        cartRepository.deleteCart(userId);
    }


    // Utility — update total amount
    private void updateTotal(Cart cart) {
        double total = 0;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }
        cart.setTotalAmount(total);
    }
}
