package com.example.bookstore.repository;

import com.example.bookstore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderRepository,Long> {

}
