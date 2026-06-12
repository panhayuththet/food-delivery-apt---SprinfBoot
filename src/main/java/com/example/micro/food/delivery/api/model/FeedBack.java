package com.example.micro.food.delivery.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name = " tbl_feedback")
public class FeedBack extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", length = 200)
    private  String comment;

    private double rating;

    private Date feedbackDate;

    private Long userId;

    private Long restaurantId;

    private Long orderId;

    private Long deliveryId;



}
