package com.example.micro.food.delivery.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DeviceResponse {

    private Long id;
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
    @JsonProperty("last_login")
    private Date lastLogin;
    private String status;


}
