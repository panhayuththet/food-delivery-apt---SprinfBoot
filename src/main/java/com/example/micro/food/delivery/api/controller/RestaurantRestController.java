package com.example.micro.food.delivery.api.controller;

import com.example.micro.food.delivery.api.dto.RestaurantRequest;
import com.example.micro.food.delivery.api.exception.ApiResponseUtil;
import com.example.micro.food.delivery.api.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
public class RestaurantRestController {

    private final RestaurantService restaurantService;


    @PostMapping("/v1/restaurants")
    public ResponseEntity<Object> create(
            @RequestBody RestaurantRequest restaurantRequest) {
        log.info("Intercept request create new restaurant: {}", restaurantRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.create(restaurantRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "/v1/restaurants/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody RestaurantRequest restaurantRequest) {

        log.info("Intercept request update Restaurant v1 with request: {}", restaurantRequest);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.update(id, restaurantRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/restaurants/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.info("Intercept request delete restaurant v1 with id: {}", id);

        restaurantService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v1/restaurants/{id}",
            produces = "application/json")
    public ResponseEntity<Object> findById(
            @PathVariable Long id) {

        log.info("Intercept request find restaurant by id: {}", id);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.getById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/restaurants",
            produces = "application/json")
    public ResponseEntity<Object> findAll() {

        log.info("Intercept request find all delivery partners");
        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.getAll()), HttpStatus.OK);
    }
}