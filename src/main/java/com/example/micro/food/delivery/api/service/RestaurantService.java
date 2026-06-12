package com.example.micro.food.delivery.api.service;

import com.example.micro.food.delivery.api.dto.RestaurantRequest;
import com.example.micro.food.delivery.api.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse create(RestaurantRequest restaurantRequest);
    RestaurantResponse update(Long id,  RestaurantRequest restaurantRequest);
    void delete(Long id);
    RestaurantResponse getById(Long id);
    List<RestaurantResponse> getAll();
}
