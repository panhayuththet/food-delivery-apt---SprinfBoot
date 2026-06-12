package com.example.micro.food.delivery.api.service.impl;

import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.RestaurantRequest;
import com.example.micro.food.delivery.api.dto.RestaurantResponse;
import com.example.micro.food.delivery.api.model.Restaurant;
import com.example.micro.food.delivery.api.repository.RestaurantRepository;
import com.example.micro.food.delivery.api.service.RestaurantService;
import com.example.micro.food.delivery.api.service.handler.RestaurantHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantHandlerService restaurantHandlerService;

    @Override
    public RestaurantResponse create(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant = restaurantHandlerService
                .convertRestaurantRequestToRestaurant(restaurantRequest, restaurant);
        log.info("Creating restaurant {}", restaurant);
        restaurantRepository.saveAndFlush(restaurant);

        return restaurantHandlerService.convertRestaurantToRestaurantResponse(restaurant);
    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequest restaurantRequest) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()) {
            Restaurant updateRestaurant = restaurantHandlerService
                    .convertRestaurantRequestToRestaurant(restaurantRequest, restaurant.get());

            updateRestaurant.setUpdatedAt(new Date());
            updateRestaurant.setUpdatedBy(Constant.SYSTEM);

            log.info("Updating restaurant {}", updateRestaurant);
            restaurantRepository.saveAndFlush(updateRestaurant);

            return restaurantHandlerService.convertRestaurantToRestaurantResponse(updateRestaurant);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()) {
            return new  RestaurantResponse();
        }
        return restaurantHandlerService.convertRestaurantToRestaurantResponse(restaurant.get());
    }

    @Override
    public List<RestaurantResponse> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if(restaurants.isEmpty()) {
            return List.of();
        }
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for(Restaurant restaurant : restaurants) {
            restaurantResponses
                    .add(restaurantHandlerService.convertRestaurantToRestaurantResponse(restaurant));
        }

        return restaurantResponses;
    }
}
