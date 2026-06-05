package com.example.micro.food.delivery.api.model;


import com.example.micro.food.delivery.api.enumeration.NotificationChannel;
import com.example.micro.food.delivery.api.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Table(name = "tbl_notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    private NotificationType notificationType;

    private NotificationChannel notificationChannel;

    private boolean read;

    private Long userId;

    private Long deviceId;



}
