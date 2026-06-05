package com.example.micro.food.delivery.api.model;


import com.example.micro.food.delivery.api.enumeration.DeliveryStatus;
import com.example.micro.food.delivery.api.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity


@Table(name = "tbl_order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();

    private double TotalAmount;
    private double deliveryFee;
    private double tax;
    private double restaurantRating;
    private double deliveryRating;
    private OrderStatus orderStatus;
    private Long userId;
    private Long restaurantId;
    private Long deliveryId;
    private Long deliveryPartnerId;
    private Long paymentId;

}
