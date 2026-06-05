package com.example.micro.food.delivery.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Table(name = "tbl_restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String category;
    private String description;
    private double rating;
    private String address;
    private String phoneNumber;
    private String logoUrl;

    @Temporal(TemporalType.TIME)
    private Date openTime;
    @Temporal(TemporalType.TIME)
    private Date closeTime;





}
