package com.example.micro.food.delivery.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class OrderRequest  {

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("delivery_time")
    private String deliveryTime;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("tax")
    private Double tax;

    @JsonProperty("delivery_fee")
    private Double deliveryFee;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_phone_number")
    private String userPhoneNumber;

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("order_items")
    private List<OrderItemRequest> orderItemRequests;

    @JsonProperty("payment")
    private PaymentRequest paymentRequest;

}
