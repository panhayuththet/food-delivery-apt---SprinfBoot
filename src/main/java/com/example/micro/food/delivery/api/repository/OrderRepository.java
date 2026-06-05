package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
