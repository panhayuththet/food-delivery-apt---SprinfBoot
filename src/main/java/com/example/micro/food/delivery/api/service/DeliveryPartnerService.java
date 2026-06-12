package com.example.micro.food.delivery.api.service;

import com.example.micro.food.delivery.api.dto.DeliveryPartnerRequest;
import com.example.micro.food.delivery.api.dto.DeliveryPartnerResponse;

import java.util.List;


public interface DeliveryPartnerService {

    DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest);
    DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest);
    void delete(Long id);
    DeliveryPartnerResponse getById(Long id);
    List<DeliveryPartnerResponse> getAll();

}
