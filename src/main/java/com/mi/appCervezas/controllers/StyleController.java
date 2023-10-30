package com.mi.appCervezas.controllers;

import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Style style = styleService.getStyleById(id);
        return ResponseEntity.ok(style);
    }
}
