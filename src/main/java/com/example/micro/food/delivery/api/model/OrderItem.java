package com.example.micro.food.delivery.api.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.jmx.export.annotation.ManagedNotification;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "tbl_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantity;
    private Double price;
    private Long menuItemId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
