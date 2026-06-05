package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
