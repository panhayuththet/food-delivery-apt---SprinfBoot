package com.example.micro.food.delivery.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "tbl_device")
public class Device extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("device_id")
    private String deviceId;
    private String deviceType;
    private String deviceModel;
    private String osVersion;
    private String appVersion;
    private Date lastLogin;
    private boolean trustDevice;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



}
