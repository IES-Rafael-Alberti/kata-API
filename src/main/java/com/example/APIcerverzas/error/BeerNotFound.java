package com.example.APIcerverzas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFound extends RuntimeException {

    public BeerNotFound(Long id) {
        super ("Cerveza no encontrada con el id " + id);
    }

}
