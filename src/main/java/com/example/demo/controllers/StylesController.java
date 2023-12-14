package com.example.demo.controllers;

import com.example.demo.models.Styles;
import com.example.demo.repositories.StylesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StylesController {

    private final StylesRepository stylesRepository;

    public StylesController(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }

    @GetMapping("/styles")
    public Page<Styles> getStyles(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Styles> stylesPage = stylesRepository.findAll(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(stylesPage).getBody();
    }

    @GetMapping("/style/{id}")
    public ResponseEntity<Styles> getStyleById(@PathVariable Long id) {
        Styles style = stylesRepository.findById(id).orElse(null);
        if (style != null) {
            return new ResponseEntity<>(style, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Style not found with id " + id);
        }
    }

}