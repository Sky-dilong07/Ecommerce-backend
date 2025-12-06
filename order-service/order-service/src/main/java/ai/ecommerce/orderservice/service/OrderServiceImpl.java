package ai.ecommerce.orderservice.service;

import ai.ecommerce.orderservice.dto.OrderItemRequest;
import ai.ecommerce.orderservice.dto.OrderRequest;
import ai.ecommerce.orderservice.entity.Order;
import ai.ecommerce.orderservice.entity.OrderItem;
import ai.ecommerce.orderservice.kafka.OrderEventProducer;
import ai.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer eventProducer;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderEventProducer eventProducer) {
        this.orderRepository = orderRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public Order placeOrder(OrderRequest orderRequest) {

        // Create Order
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(itemReq.getPrice());
            item.setOrder(order);

            total += itemReq.getPrice() * itemReq.getQuantity();
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Send Kafka event
        eventProducer.sendOrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalAmount()
        );

        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
