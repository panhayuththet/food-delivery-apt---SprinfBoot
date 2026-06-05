package com.example.micro.food.delivery.api.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j


public class UserHandlerService {

    public void usernameHashText(String username) {
        if (StringUtils.hasText(username)) {
            return;
        }
        log.error("Username is null or empty");
        throw new IllegalArgumentException("Username is empty");
    };

    public void phoneNumberHashText(String phoneNumber){
         if(StringUtils.hasText(phoneNumber)) {
             return;
         }
         log.error("Phone number is null or empty");
         throw new IllegalArgumentException("Phone number is empty");
    }

}




