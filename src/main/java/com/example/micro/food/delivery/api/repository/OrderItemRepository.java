package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
