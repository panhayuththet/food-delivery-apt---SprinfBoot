package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
