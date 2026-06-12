package com.example.micro.food.delivery.api.repository;

import com.example.micro.food.delivery.api.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Long> {

    List<DeliveryPartner> findAllByAvailable(boolean available);



}
