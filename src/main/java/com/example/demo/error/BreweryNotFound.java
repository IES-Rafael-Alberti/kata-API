package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BreweryNotFound extends RuntimeException {
    public BreweryNotFound(Long id) {
        super("No se puede encontrar la cerveceria con la ID: " + id);
    }
}
