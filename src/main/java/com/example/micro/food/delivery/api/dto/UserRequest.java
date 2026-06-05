package com.example.micro.food.delivery.api.dto;

import com.example.micro.food.delivery.api.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

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
    private String phone;
    private String address;
    @JsonProperty("user_type")
    private UserType userType;
    private String status;

    @JsonProperty("device_info")
    private DeviceRequest deviceRequest;


    @Getter
    @Setter
    public static class DeviceRequest {
        @JsonProperty("device_id")
        private String deviceId;
        @JsonProperty("device_type")
        private String deviceType;
        @JsonProperty("device_model")
        private String deviceModel;
        @JsonProperty("os_version")
        private String osVersion;
        @JsonProperty("app_version")
        private String appVersion;
        @JsonProperty("trust_device")
        private boolean trustDevice;
    }


}
