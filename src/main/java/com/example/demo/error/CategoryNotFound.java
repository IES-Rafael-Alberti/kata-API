package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

        @ResponseStatus(HttpStatus.NOT_FOUND)
        public class CategoryNotFound extends RuntimeException{
            public CategoryNotFound(Long id) {
                super("No se puede encontrar la categoria con la ID: " + id);
            }
        }

