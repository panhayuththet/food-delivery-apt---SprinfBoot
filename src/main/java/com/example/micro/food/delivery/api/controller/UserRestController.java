package com.example.micro.food.delivery.api.controller;


import com.example.micro.food.delivery.api.dto.UserRequest;
import com.example.micro.food.delivery.api.dto.UserResponse;
import com.example.micro.food.delivery.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
// this is lombok props if we don't use this annotation we have to call constructure on UserService
@RequiredArgsConstructor
public class UserRestController {


    private final UserService userService;

    @PostMapping(value = "v1/users", consumes = "application/json", produces = "application/json")
    private ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        log.info("Intercept request create new user v1 with request: {}", userRequest);

        userService.create(userRequest);
        return ResponseEntity.ok().build();
    }

}
