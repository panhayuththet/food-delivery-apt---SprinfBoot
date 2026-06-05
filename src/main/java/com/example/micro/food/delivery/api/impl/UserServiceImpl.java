package com.example.micro.food.delivery.api.impl;

import com.example.micro.food.delivery.api.dto.UserRequest;
import com.example.micro.food.delivery.api.dto.UserResponse;
import com.example.micro.food.delivery.api.model.Device;
import com.example.micro.food.delivery.api.model.User;
import com.example.micro.food.delivery.api.repository.DeviceRepository;
import com.example.micro.food.delivery.api.repository.UserRepository;
import com.example.micro.food.delivery.api.service.UserHandlerService;
import com.example.micro.food.delivery.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserHandlerService userHandlerService;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository,
                           DeviceRepository deviceRepository,
                           UserHandlerService userHandlerService,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userHandlerService = userHandlerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        userHandlerService.usernameHashText(userRequest.getUsername());
        userHandlerService.phoneNumberHashText(userRequest.getPhone());

        User user = modelMapper.map(userRequest, User.class);
        log.info("Creating user: {}", user);
        userRepository.save(user);

        Device model = modelMapper.map(userRequest.getDeviceRequest(), Device.class);
        return null;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }

    public static class Devive {

    }
}
