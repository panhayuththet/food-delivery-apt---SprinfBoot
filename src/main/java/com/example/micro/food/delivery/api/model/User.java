package com.example.micro.food.delivery.api.model;

import com.example.micro.food.delivery.api.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "devices")


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
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "phone_number",  length = 15, nullable = false)
    private String phoneNumber;
    private String address;
    private String email;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices ;


}
