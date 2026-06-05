package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {



}
