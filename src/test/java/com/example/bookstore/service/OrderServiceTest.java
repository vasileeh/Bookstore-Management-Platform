package com.example.bookstore.service;

import com.example.bookstore.model.Customer;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testCreateOrder() {
        OrderItem item = new OrderItem();
        item.setPrice(20.0);
        item.setQuantity(2);

        Order order = new Order();
        order.setCustomer(new Customer());
        order.setItems(List.of(item));

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order saved = orderService.createOrder(order);

        assertEquals(40.0, saved.getTotalAmount());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(), new Order()));
        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteOrder() {
        orderService.deleteOrder(1L);
        verify(orderRepository).deleteById(1L);
    }
}
