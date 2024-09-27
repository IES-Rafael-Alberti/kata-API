package com.mi.appCervezas.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {

        System.out.println("Se ha producido un error: " + ex.getMessage());

        String mensajeError = "Ocurrió un error en el servidor.";
        return ResponseEntity.status(500).body(mensajeError);
    }
}
