package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
