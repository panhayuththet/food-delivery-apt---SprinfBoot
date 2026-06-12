package com.example.micro.food.delivery.api.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateTimeUtils {

    private DateTimeUtils() {

    }

    public static Date convertStringToDate(String dateOfBirth) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            log.error("Error converting date: ", e);
        }
        return new Date();
    }

    public static LocalTime convertStringTimeToLocalTime(String strTime) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            return LocalTime.parse(strTime.trim().toUpperCase(), formatter);

        } catch (Exception e) {
            log.error("Error parsing time: {}", strTime, e);
            throw new RuntimeException("Invalid time format. Expected: 8:30 PM or 20:30");
        }
    }
}
