package com.mi.appCervezas.controllers;

import com.mi.appCervezas.error.StyleNotFoundException;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public List<Style> getAllStyles() {
        return styleService.getAllStyles();
    }

    @GetMapping("/style/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        try {
            Style style = styleService.getStyleById(id);
            return ResponseEntity.ok(style);
        } catch (StyleNotFoundException ex) {
            // Si la excepción es lanzada, devolvemos un HttpStatus.NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
