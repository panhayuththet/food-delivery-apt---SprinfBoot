package com.example.micro.food.delivery.api.model;


import com.example.micro.food.delivery.api.enumeration.PaymentMethod;
import com.example.micro.food.delivery.api.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "tbl_payment")
public class Payment extends BaseEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String description;
    private Long orderId;




}
