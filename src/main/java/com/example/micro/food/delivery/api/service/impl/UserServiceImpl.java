package com.example.micro.food.delivery.api.service.impl;

import com.example.micro.food.delivery.api.exception.UserNotFoundErrorException;
import com.example.micro.food.delivery.api.utils.DateTimeUtils;
import com.example.micro.food.delivery.api.dto.UserRequest;
import com.example.micro.food.delivery.api.dto.UserResponse;
import com.example.micro.food.delivery.api.model.Device;
import com.example.micro.food.delivery.api.model.User;
import com.example.micro.food.delivery.api.repository.DeviceRepository;
import com.example.micro.food.delivery.api.repository.UserRepository;
import com.example.micro.food.delivery.api.service.handler.UserHandlerService;
import com.example.micro.food.delivery.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserHandlerService userHandlerService;

    public UserServiceImpl(
            UserRepository userRepository,
            DeviceRepository deviceRepository,
            UserHandlerService userHandlerService,
            ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userHandlerService = userHandlerService;
    }

    @Transactional //Roll Back
    @Override

    public UserResponse create(UserRequest userRequest) {

        userHandlerService.usernameHashText(userRequest.getUsername());
        userHandlerService.phoneNumberHashText(userRequest.getPhoneNumber());

        User user = userHandlerService.convertUserRequestToUser(userRequest);
        log.info("Save user record: {}", user);

        userRepository.saveAndFlush(user);

        if (user.getId() != null) {
            Device device = userHandlerService.convertDeviceRequestToDevice(user, userRequest.getDeviceRequest());
            log.info("Save device record: {}", device);
            deviceRepository.save(device);
        }
        return userHandlerService.convertUserToUserResponse(user);
    }
    @Transactional//If not find rollback
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            log.info("User update with id {} not found DB", id);
            this.create(userRequest);
            return null;
        }
        user.get().setUsername(userRequest.getUsername());
        user.get().setPhoneNumber(userRequest.getPhoneNumber());
        user.get().setDateOfBirth(DateTimeUtils.convertStringToDate(userRequest.getDateOfBirth()));
        user.get().setGender(userRequest.getGender());
        user.get().setEmail(userRequest.getEmail());
        user.get().setEmail(userRequest.getEmail());
        user.get().setFirstName(userRequest.getFirstName());
        user.get().setLastName(userRequest.getLastName());
        user.get().setUpdatedAt(new Date());

        log.info("Update user record: {}", user.get());
        userRepository.saveAndFlush(user.get());

        if(user.get().getDevices() != null) {
            var deviceRequest = userRequest.getDeviceRequest();
            if(StringUtils.hasText(deviceRequest.getDeviceId())) {
                List<Device> devices = user.get().getDevices();
                Device deviceUpdate = devices.stream()
                        .filter(device ->
                                device.getDeviceId().equalsIgnoreCase(deviceRequest.getDeviceId()))
                        .findAny().orElse(null);

                if(deviceUpdate != null) {
                    deviceUpdate.setDeviceModel(deviceRequest.getDeviceModel());
                    deviceUpdate.setDeviceType(deviceRequest.getDeviceType());
                    deviceUpdate.setOsVersion(deviceRequest.getOsVersion());
                    deviceUpdate.setAppVersion(deviceRequest.getAppVersion());
                    deviceUpdate.setTrustDevice(deviceRequest.isTrustDevice());

                    log.info("Update device record: {}", deviceUpdate);
                    deviceRepository.save(deviceUpdate);

                }else {
                    Device device = userHandlerService.convertDeviceRequestToDevice(user.get(), deviceRequest);
                    deviceRepository.save(device);

                }
            }
        }

        return userHandlerService.convertUserToUserResponse(user.get());
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            log.info("User with id {} not found in DB." , id);
            throw new UserNotFoundErrorException("User with id " + id + " not found");
        }
        return userHandlerService.convertUserToUserResponse(user.get());
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> findAll() {

        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            log.info("No users found in DB.");
            throw new UserNotFoundErrorException("No users found");
        }

        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            UserResponse userResponse = userHandlerService.convertUserToUserResponse(user);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public static class Devive {

    }
}
