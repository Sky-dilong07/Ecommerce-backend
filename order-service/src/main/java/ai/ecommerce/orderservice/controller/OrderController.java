package ai.ecommerce.orderservice.controller;

import ai.ecommerce.orderservice.dto.OrderRequest;
import ai.ecommerce.orderservice.entity.Order;
import ai.ecommerce.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // PLACE ORDER
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(savedOrder);
    }

    // GET ALL ORDERS
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // GET ORDERS BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    // HEALTH CHECK
    @GetMapping("/health")
    public String health() {
        return "Order Service is running";
    }
}
