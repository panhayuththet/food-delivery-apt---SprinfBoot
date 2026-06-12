package com.example.micro.food.delivery.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryPartnerResponse {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dob")
    private String dateOfBirth;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("vehicle")
    private String vehicle;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("update_at")
    private String updateAt;

    @JsonProperty("update_by")
    private String updateBy;

}
