package com.example.kataAPI.errors;

import com.example.kataAPI.errors.custom_exceptions.Beer_exist;
import com.example.kataAPI.errors.custom_exceptions.Not_found_beer;
import com.example.kataAPI.errors.custom_exceptions.Not_found_exception;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Global_exception_handler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handle_exception(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = {Not_found_exception.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Error_response> handle_Not_found_exception(Not_found_exception ex) {
        Error_response Error_response = new Error_response(HttpStatus.NOT_FOUND, ex.getMessage());
        Error_response.setCode(HttpStatus.NOT_FOUND);
        Error_response.setMessage(ex.getMessage());
        return new ResponseEntity<>(Error_response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Not_found_beer.class})
    public ResponseEntity<Error_response> handle_Not_found_beer(Not_found_beer ex){
        Error_response Error_response = new Error_response(HttpStatus.NOT_FOUND, ex.getMessage());
        Error_response.setCode(HttpStatus.NOT_FOUND);
        Error_response.setMessage(ex.getMessage());
        return new ResponseEntity<>(Error_response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Beer_exist.class})
    public ResponseEntity<Error_response> handle_Beer_exist(Beer_exist ex){
        Error_response Error_response = new Error_response( HttpStatus.CONFLICT, ex.getMessage());
        Error_response.setCode(HttpStatus.CONFLICT);
        Error_response.setMessage(ex.getMessage());
        return new ResponseEntity<>(Error_response, HttpStatus.CONFLICT);
    }

}
