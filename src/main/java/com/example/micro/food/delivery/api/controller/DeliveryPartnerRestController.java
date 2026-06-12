package com.example.micro.food.delivery.api.controller;

import com.example.micro.food.delivery.api.dto.DeliveryPartnerRequest;
import com.example.micro.food.delivery.api.exception.ApiResponseUtil;
import com.example.micro.food.delivery.api.service.DeliveryPartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DeliveryPartnerRestController {

    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping(value = "/v1/delivery-partner", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(
            @RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {
        log.info("Intercept request create delivery partner v1 with request: {}", deliveryPartnerRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(deliveryPartnerService.create(deliveryPartnerRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "/v1/delivery-partner/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {

        log.info("Intercept request update delivery partner v1 with request: {}", deliveryPartnerRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(deliveryPartnerService.update(id,deliveryPartnerRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/delivery-partner/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.info("Intercept request delete delivery partner v1 with id: {}", id);

        deliveryPartnerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v1/delivery-partner/{id}",
            produces = "application/json")
    public ResponseEntity<Object> getById(
            @PathVariable Long id) {

        log.info("Intercept request find delivery partner by id: {}", id);

        return new ResponseEntity<>(deliveryPartnerService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/delivery-partner",
            produces = "application/json")
    public ResponseEntity<Object> getAll() {

        log.info("Intercept request find all delivery partners");
        return new ResponseEntity<>(deliveryPartnerService.getAll(), HttpStatus.OK);
    }
}