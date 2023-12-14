package com.example.demo.controllers;

import com.example.demo.models.Breweries;
import com.example.demo.repositories.BreweriesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BreweriesController {

    private final BreweriesRepository breweriesRepository;

    public BreweriesController(BreweriesRepository breweriesRepository) {
        this.breweriesRepository = breweriesRepository;
    }

    @GetMapping("/breweries")
    public Page<Breweries> getBreweries(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Breweries> breweriesPage = breweriesRepository.findAll(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(breweriesPage).getBody();
    }

    @GetMapping("/brewerie/{id}")
    public ResponseEntity<Breweries> getBrewerieById(@PathVariable Long id) {
        Breweries brewerie = breweriesRepository.findById(id).orElse(null);
        if (brewerie != null) {
            return new ResponseEntity<>(brewerie, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Brewerie not found with id " + id);
        }
    }

}
