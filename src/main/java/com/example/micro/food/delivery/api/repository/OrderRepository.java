package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.enumeration.OrderStatus;
import com.example.micro.food.delivery.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByOrderId(String orderId);

    Optional<Order> findFirstByUserIdAndOrderStatusNot(Long userId, OrderStatus orderStatus);

}
