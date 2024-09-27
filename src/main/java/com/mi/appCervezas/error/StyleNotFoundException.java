package com.mi.appCervezas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StyleNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 987654321L;

    public StyleNotFoundException(String id) {
        super("No se puede encontrar el estilo con Id: " + id.toString());
    }
}
