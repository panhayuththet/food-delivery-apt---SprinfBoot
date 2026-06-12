package com.example.micro.food.delivery.api.exception;

public class UserNotFoundErrorException extends RuntimeException {

    public UserNotFoundErrorException(String message) {
        super(message);
    }


}
