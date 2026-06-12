package com.example.micro.food.delivery.api.service.impl;


import com.example.micro.food.delivery.api.dto.PaymentRequest;
import com.example.micro.food.delivery.api.service.PaymentService;
import com.example.micro.food.delivery.api.service.handler.PaymentHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentHandlerService paymentHandlerService;

    public PaymentServiceImpl(PaymentHandlerService paymentHandlerService) {
        this.paymentHandlerService = paymentHandlerService;
    }


    @Override
    public String pay(PaymentRequest paymentRequest) {
        return paymentHandlerService.postingToPaymentGateway(paymentRequest);
    }

    @Override
    public String inquiry(String orderId) {
        return "";
    }
}
