package com.example.micro.food.delivery.api.model;

import com.example.micro.food.delivery.api.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "tbl_delivery")
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private Date pickUpTime;

    @Temporal(TemporalType.TIME)
    private Date deliveryTime;

    private String pickupAddress;
    private String deliveryAddress;
    private double deliveryFee;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Long deliveryPartnerId;
    private Long orderId;



}
