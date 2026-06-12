package com.example.micro.food.delivery.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RestaurantRequest {

    private String code;
    private String name;
    private String category;
    private String description;
    private String address;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("open_time")
    private String openTime;
    @JsonProperty("close_time")
    private String closeTime;


}
