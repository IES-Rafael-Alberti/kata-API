package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.error.StyleNotFoundException;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public List<StyleDTO> getAllStyles() {
        List<StyleDTO> styleDTOs = new ArrayList<>();
        List<StyleDTO> styles = styleService.getAllStyles();

        for (StyleDTO style : styles) {
            styleDTOs.add(new StyleDTO(style));
        }

        return styleDTOs;
    }

    @GetMapping("/style/{id}")
    public ResponseEntity<StyleDTO> getStyleById(@PathVariable Long id) {
        try {
            Style style = styleService.getStyleById(id);
            StyleDTO styleDTO = new StyleDTO(style);
            return ResponseEntity.ok(styleDTO);
        } catch (StyleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
