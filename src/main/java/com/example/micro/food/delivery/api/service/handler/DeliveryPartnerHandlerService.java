package com.example.micro.food.delivery.api.service.handler;


import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.DeliveryPartnerResponse;
import com.example.micro.food.delivery.api.enumeration.VehicleType;
import com.example.micro.food.delivery.api.utils.DateTimeUtils;
import com.example.micro.food.delivery.api.dto.DeliveryPartnerRequest;
import com.example.micro.food.delivery.api.model.DeliveryPartner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class DeliveryPartnerHandlerService {

    public DeliveryPartner convertDeliveryPartnerRequestToDeliveryPartner(DeliveryPartner deliveryPartner,
            DeliveryPartnerRequest deliveryPartnerRequest) {

        deliveryPartner.setFirstName(deliveryPartnerRequest.getFirstName());
        deliveryPartner.setLastName(deliveryPartnerRequest.getLastName());
        deliveryPartner.setUsername(deliveryPartnerRequest.getUsername());
        deliveryPartner.setPassword(deliveryPartnerRequest.getPassword());
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());
        deliveryPartner.setPhoneNumber(deliveryPartnerRequest.getPhoneNumber());
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setDateOfBirth(DateTimeUtils.
                convertStringToDate(deliveryPartnerRequest.getDateOfBirth()));
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setVehicleType(VehicleType.valueOf(deliveryPartnerRequest.getVehicle()));
        deliveryPartner.setAvailable(deliveryPartnerRequest.isAvailable());
        if (deliveryPartner.getId() == null) {
            deliveryPartner.setCreatedBy(Constant.SYSTEM);
            deliveryPartner.setCreatedAt(new Date());
        }
        return deliveryPartner;
    }

    public DeliveryPartnerResponse convertDeliveryPartnerRequestToDeliveryPartnerResponse(
            DeliveryPartner deliveryPartner) {

        DeliveryPartnerResponse response = new DeliveryPartnerResponse();

        response.setId(deliveryPartner.getId());
        response.setFirstName(deliveryPartner.getFirstName());
        response.setLastName(deliveryPartner.getLastName());
        response.setUsername(deliveryPartner.getUsername());
        response.setGender(deliveryPartner.getGender());
        response.setDateOfBirth(deliveryPartner.getDateOfBirth().toString());
        response.setPhoneNumber(deliveryPartner.getPhoneNumber());
        response.setEmail(deliveryPartner.getEmail());
        response.setAddress(deliveryPartner.getAddress());
        response.setVehicle(deliveryPartner.getVehicleType().toString());

        // Fixed
        response.setAvailable(deliveryPartner.isAvailable());

        response.setCreatedBy(deliveryPartner.getCreatedBy());
        response.setCreatedAt(deliveryPartner.getCreatedAt().toString());

        // Fixed
        response.setUpdateBy(deliveryPartner.getUpdatedBy());

        // Fixed
        if (deliveryPartner.getUpdatedAt() != null) {
            response.setUpdateAt(deliveryPartner.getUpdatedAt().toString());
        }

        return response;
    }


}
