package com.example.micro.food.delivery.api.dto;

import com.example.micro.food.delivery.api.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString

public class UserRequest {

    private Long id;
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    @JsonProperty("dob")
    private String dateOfBirth;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @JsonProperty("user_type")
    private UserType userType;
    private String status;

    @JsonProperty("device_info")
    private DeviceRequest deviceRequest;






}
