package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StyleNotFound extends RuntimeException {
    public StyleNotFound(Long id) {
        super("No se puede encontrar el estilo   con la ID: " + id);
    }



}

