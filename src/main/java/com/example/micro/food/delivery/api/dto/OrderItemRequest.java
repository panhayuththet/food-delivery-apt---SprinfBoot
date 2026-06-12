package com.example.micro.food.delivery.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class OrderItemRequest {

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private double price;

    @JsonProperty("order_manu_item")
    List<OrderMenuItemRequest> orderMenuItemRequests;

}
