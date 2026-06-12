package com.example.micro.food.delivery.api.service;

import com.example.micro.food.delivery.api.dto.OrderRequest;
import com.example.micro.food.delivery.api.dto.OrderResponse;
import com.example.micro.food.delivery.api.dto.OrderStatusRequest;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest orderRequest);

    OrderResponse update(OrderStatusRequest orderStatusRequest);

    void delete(Long id);

    OrderResponse getById(Long id);

    List<OrderResponse> getAll( );
}
