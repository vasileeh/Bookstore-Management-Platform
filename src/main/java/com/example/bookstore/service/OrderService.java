package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());

        double total = calculateTotal(order.getItems());
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    private double calculateTotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The order was not found"));
    }

    public Order updateOrder(Long id, Order updateOrder){
        Order existing=getOrderById(id);
        existing.setCustomer(updateOrder.getCustomer());
        existing.setOrderDate(updateOrder.getOrderDate());
        existing.setItems(updateOrder.getItems());
        existing.setTotalAmount(updateOrder.getTotalAmount());

        return orderRepository.save(existing);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
