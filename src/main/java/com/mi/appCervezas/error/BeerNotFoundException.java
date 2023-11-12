package com.mi.appCervezas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 123456789L;

    public BeerNotFoundException(Long id) {
        super("No se puede encontrar la cerveza con Id: " + id);
    }
}
