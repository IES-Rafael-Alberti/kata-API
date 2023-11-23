package com.example.kataAPI.errors.custom_exceptions;

public class Not_found_exception extends RuntimeException {
    public Not_found_exception(String message){
        super(message);
    }

}
