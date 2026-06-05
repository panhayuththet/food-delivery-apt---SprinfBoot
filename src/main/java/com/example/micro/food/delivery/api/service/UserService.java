package com.example.micro.food.delivery.api.service;


import com.example.micro.food.delivery.api.dto.UserRequest;
import com.example.micro.food.delivery.api.dto.UserResponse;

import java.util.List;

public interface UserService {

        UserResponse create(UserRequest userRequest);
        UserResponse update(Long id, UserRequest userRequest);
        UserResponse findById(Long id);
        void delete(Long id);
        List<UserResponse> findAll();


}
