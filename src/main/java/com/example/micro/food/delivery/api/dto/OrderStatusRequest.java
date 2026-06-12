package com.example.micro.food.delivery.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStatusRequest {

    private Long id;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_status")
    private String orderStatus;



}
