package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
