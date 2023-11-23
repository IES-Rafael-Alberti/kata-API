package com.example.kataAPI.errors.custom_exceptions;

public class Beer_exist extends RuntimeException {
    public Beer_exist (String message) {
        super(message);
    }
}
