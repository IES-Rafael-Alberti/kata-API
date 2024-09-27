package com.mi.appCervezas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 876543210L;

    public CategoryNotFoundException(String id) {
        super("No se puede encontrar la categoría con Id: " + id.toString());
    }
}
