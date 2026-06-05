package com.example.micro.food.delivery.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_menu_item_photo")
public class MenuItemPhoto extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileType;
    private String fileFormat;
    private Double fileSize;
    private String fileName;
    private String smallUrl;
    private String mediumUrl;
    private String largeUrl;
    private String uploadedBy;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;



}
