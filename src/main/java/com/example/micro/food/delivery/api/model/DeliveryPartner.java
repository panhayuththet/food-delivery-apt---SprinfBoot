package com.example.micro.food.delivery.api.model;

import com.example.micro.food.delivery.api.enumeration.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_delivery_partner")
public class DeliveryPartner extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    @Column(name = "username",  unique = true , nullable = false)
    private String username;
    private String password;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private boolean available;


}
