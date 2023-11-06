package com.mi.appCervezas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 43876691117560211L;

    public ProductNotFoundException(Long id) {
        super("No se puede encontrar el producto con Id: " + id);
    }
}
