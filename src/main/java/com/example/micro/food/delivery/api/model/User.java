package com.example.micro.food.delivery.api.model;


import com.example.micro.food.delivery.api.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",  unique = true, nullable = false)
    private String username;

    private String firstName;

    private String lastName;
    @Column(name = "gender", length = 10)
    private String gender;

//  @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "phone_number",  length = 15, nullable = false)
    private String phone;

    private String address;

    private String email;

    private UserType userType;

    private String status;


}
