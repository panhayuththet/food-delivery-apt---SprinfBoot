package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    Optional<Delivery> findFirstByOrderId(long orderId);


}
