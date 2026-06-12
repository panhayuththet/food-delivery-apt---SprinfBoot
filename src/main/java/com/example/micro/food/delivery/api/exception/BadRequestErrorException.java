package com.example.micro.food.delivery.api.exception;

public class BadRequestErrorException extends RuntimeException {

    public BadRequestErrorException(String message) {
        super(message);
    }


}
