package com.example.kataAPI.errors.custom_exceptions;



public class Not_found_beer extends RuntimeException {

    public Not_found_beer (String message) {
        super(message);
    }

}
