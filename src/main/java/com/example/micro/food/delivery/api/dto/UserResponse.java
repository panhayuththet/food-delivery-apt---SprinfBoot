package com.example.micro.food.delivery.api.dto;


import com.example.micro.food.delivery.api.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    @JsonProperty("dob")
    private LocalDateTime dateOfBirth;
    private String email;
    @JsonProperty("phone_number")
    private String phone;
    private String address;
    @JsonProperty("user_type")
    private UserType userType;
    private String status;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated-at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private String updatedBy;
}
