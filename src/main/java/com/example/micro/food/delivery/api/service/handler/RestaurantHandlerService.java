package com.example.micro.food.delivery.api.service.handler;


import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.RestaurantRequest;
import com.example.micro.food.delivery.api.dto.RestaurantResponse;
import com.example.micro.food.delivery.api.model.Restaurant;
import com.example.micro.food.delivery.api.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class RestaurantHandlerService {

    public Restaurant convertRestaurantRequestToRestaurant(RestaurantRequest restaurantRequest,
            Restaurant restaurant) {

        restaurant.setName(restaurantRequest.getName());
        restaurant.setCategory(restaurantRequest.getCategory());
        restaurant.setCode(restaurantRequest.getCode());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setRating(restaurantRequest.getRating());
        restaurant.setLogoUrl(restaurantRequest.getLogoUrl());
        restaurant.setOpenTime(DateTimeUtils.convertStringTimeToLocalTime(restaurantRequest.getOpenTime()));
        restaurant.setCloseTime(DateTimeUtils.convertStringTimeToLocalTime(restaurantRequest.getCloseTime()));
        if(restaurant.getId() == null) {
            restaurant.setCreatedAt(new Date());
            restaurant.setUpdatedBy(Constant.SYSTEM);
        }

        return restaurant;
    }

    public RestaurantResponse convertRestaurantToRestaurantResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .Id(restaurant.getId())
                .name(restaurant.getName())
                .code(restaurant.getCode())
                .category(restaurant.getCategory())
                .description(restaurant.getDescription())
                .rating(restaurant.getRating())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .logoUrl(restaurant.getLogoUrl())
                .openTime(restaurant.getOpenTime().toString())
                .closeTime(restaurant.getCloseTime().toString())
                .build();
    }


}
