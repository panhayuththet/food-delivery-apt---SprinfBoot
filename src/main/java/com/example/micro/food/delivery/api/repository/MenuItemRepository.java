package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
}
