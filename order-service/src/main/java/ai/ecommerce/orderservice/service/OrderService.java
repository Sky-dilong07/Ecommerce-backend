package ai.ecommerce.orderservice.service;

import ai.ecommerce.orderservice.dto.OrderRequest;
import ai.ecommerce.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);
}
