package com.example.micro.food.delivery.api.service.impl;

import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.DeliveryPartnerRequest;
import com.example.micro.food.delivery.api.dto.DeliveryPartnerResponse;
import com.example.micro.food.delivery.api.model.DeliveryPartner;
import com.example.micro.food.delivery.api.repository.DeliveryPartnerRepository;
import com.example.micro.food.delivery.api.service.DeliveryPartnerService;
import com.example.micro.food.delivery.api.service.handler.DeliveryPartnerHandlerService;

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
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final DeliveryPartnerHandlerService deliveryPartnerHandlerService;

    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner = deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartner,  deliveryPartnerRequest);
        log.info("Save new Delivery Partner record: {}", deliveryPartner);
        deliveryPartnerRepository.saveAndFlush(deliveryPartner);

        if(deliveryPartner.getId() != null) {
            return deliveryPartnerHandlerService.convertDeliveryPartnerRequestToDeliveryPartnerResponse(deliveryPartner);
        }

        return null;
    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {

        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            return new DeliveryPartnerResponse();
        }

        DeliveryPartner updateDeliveryPartner = deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartner.get(), deliveryPartnerRequest);

        updateDeliveryPartner.setUpdatedBy(Constant.SYSTEM);
        updateDeliveryPartner.setUpdatedAt(new Date());

        deliveryPartnerRepository.saveAndFlush(updateDeliveryPartner);

        return deliveryPartnerHandlerService.convertDeliveryPartnerRequestToDeliveryPartnerResponse(updateDeliveryPartner);
    }

    @Override
    public void delete(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            log.info("Delete Delivery Partner record not found with id: {}", id);
            return;
        }
        deliveryPartnerRepository.deleteById(id);
    }

    @Override
    public DeliveryPartnerResponse getById(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            log.info("Find Delivery Partner by id {} not found.", id);
            return new DeliveryPartnerResponse();
        }
        return deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartnerResponse(deliveryPartner.get());
    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {
        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAll();
        if(deliveryPartners.isEmpty()) {
            log.info("Fetch all delivery Partner records from DB.");
            return List.of();
        }

        List<DeliveryPartnerResponse> deliveryPartnerResponses = new ArrayList<>();
        for( DeliveryPartner deliveryPartner : deliveryPartners) {

            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerHandlerService
                    .convertDeliveryPartnerRequestToDeliveryPartnerResponse(deliveryPartner);

            deliveryPartnerResponses.add(deliveryPartnerResponse);
        }
        return deliveryPartnerResponses;
    }
}
