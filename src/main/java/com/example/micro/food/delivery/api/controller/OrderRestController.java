package com.example.micro.food.delivery.api.controller;

import com.example.micro.food.delivery.api.dto.OrderRequest;
import com.example.micro.food.delivery.api.dto.OrderStatusRequest;
import com.example.micro.food.delivery.api.exception.ApiResponseUtil;
import com.example.micro.food.delivery.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping(value = "/v1/orders", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody OrderRequest orderRequest) {

        log.info("Intercept request create new order v1 with request: {}", orderRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.create(orderRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "v1/orders", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> update(@RequestBody OrderStatusRequest orderStatusRequest) {
        log.info("Intercept request update order v1 with request: {}", orderStatusRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.update(orderStatusRequest)), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/orders/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        log.info("Intercept request get Order v1 with id: {}", id);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.getById(id)), HttpStatus.OK
        );
    }

    @GetMapping(value = "/v1/orders", produces = "application/json")
    public ResponseEntity<Object> getAll() {

        log.info("Intercept request get all Order v1");
        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.getAll()), HttpStatus.OK);
    }

}
