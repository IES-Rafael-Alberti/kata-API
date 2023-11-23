package com.example.kataAPI.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Error_response {

    private HttpStatus code;
    private String message;

    public Error_response(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }


}
