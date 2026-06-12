package com.example.micro.food.delivery.api.service.handler;

import com.example.micro.food.delivery.api.utils.DateTimeUtils;
import com.example.micro.food.delivery.api.dto.DeviceRequest;
import com.example.micro.food.delivery.api.dto.DeviceResponse;
import com.example.micro.food.delivery.api.dto.UserRequest;
import com.example.micro.food.delivery.api.dto.UserResponse;
import com.example.micro.food.delivery.api.model.Device;
import com.example.micro.food.delivery.api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

import static com.example.micro.food.delivery.api.constant.Constant.SYSTEM;
import static com.example.micro.food.delivery.api.constant.Constant.USER_STATUS_ACTIVE;

@Service
@Slf4j
public class UserHandlerService {

    public void usernameHashText(String username) {
        if (StringUtils.hasText(username)) {
            return;
        }
        log.error("Username is empty");
        throw new IllegalArgumentException("Username is empty");
    };

    public void phoneNumberHashText(String phoneNumber){
         if(StringUtils.hasText(phoneNumber)) {
             return;
         }
         log.error("Phone number empty");
         throw new IllegalArgumentException("Phone number is empty");
    }

    public UserResponse convertUserToUserResponse (final User user) {

         return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth().toString())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                 .email(user.getEmail())
                .userType(user.getUserType())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedBy(user.getUpdatedBy())
                .updatedAt(user.getUpdatedAt())
                .build();
}

    public User convertUserRequestToUser(final UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setDateOfBirth(DateTimeUtils.convertStringToDate(userRequest.getDateOfBirth()));
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAddress(userRequest.getAddress());

        user.setUserType(userRequest.getUserType());

        user.setStatus(USER_STATUS_ACTIVE);
        user.setCreatedBy(SYSTEM);
        user.setUpdatedBy(SYSTEM);
        user.setCreatedAt(new Date());

        return user;
    }

    public Device convertDeviceRequestToDevice(final User user,final DeviceRequest deviceRequest) {
        Device device = new Device();
        device.setDeviceId(deviceRequest.getDeviceId());
        device.setDeviceModel(deviceRequest.getDeviceModel());
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setOsVersion(deviceRequest.getOsVersion());
        device.setAppVersion(deviceRequest.getAppVersion());
        device.setTrustDevice(deviceRequest.isTrustDevice());
        device.setUser(user);

        return device;
    }

    public DeviceResponse convertUserDeviceToDeviceResponse(final Device device) {

        return DeviceResponse.builder()
                .id(device.getId())
                .deviceId(device.getDeviceId())
                .deviceModel(device.getDeviceModel())
                .deviceType(device.getDeviceType())
                .trustDevice(device.isTrustDevice())
                .osVersion(device.getOsVersion())
                .appVersion(device.getAppVersion())
                .lastLogin(device.getLastLogin())
                .status(device.getStatus())
                .build();

    }

}
