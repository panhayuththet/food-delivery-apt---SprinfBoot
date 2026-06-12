package com.example.micro.food.delivery.api.exception;

import java.time.LocalDateTime;

public class ApiResponseUtil {

    public static ApiResponseEntityDto ctreateApiResponseEntityDto(
             String message, String errorDescription ,String errorCode, int statusCode, Object responseData) {

        return ApiResponseEntityDto.builder()
                .message(message)
                .errorCode(errorCode)
                .statusCode(statusCode)
                .messageDescription(errorDescription)
                .timestamp(LocalDateTime.now())
                .responseData(responseData)
                .build();

    }

    public static ApiResponseEntityDto successResponse(Object object) {

        return ApiResponseUtil
                    .ctreateApiResponseEntityDto(
                        "Success",
                        "Success",
                        "200",
                        200,
                        object);
    }

}
