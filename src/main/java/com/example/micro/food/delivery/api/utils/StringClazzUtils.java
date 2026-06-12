package com.example.micro.food.delivery.api.utils;

public class StringClazzUtils {

    private StringClazzUtils() {

    }

    public static String getFileExtension(String file) {
        int index =  file.lastIndexOf(".");
        if (index < 0) {
            return null;
        }
        return file.substring(index + 1);
    }
}
