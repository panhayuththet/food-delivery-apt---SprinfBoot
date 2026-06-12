package com.example.micro.food.delivery.api.service;

import com.example.micro.food.delivery.api.dto.PaymentRequest;

public interface PaymentService {

    String pay(PaymentRequest paymentRequest);

    String inquiry(String orderId);


}
