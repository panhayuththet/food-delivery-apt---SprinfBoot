package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
