package com.example.micro.food.delivery.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_menu_item")
public class MenuItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String description;
    private double price;
    private Integer availability;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

}
