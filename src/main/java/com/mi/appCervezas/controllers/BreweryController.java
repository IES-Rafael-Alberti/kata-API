package com.mi.appCervezas.controllers;

import com.mi.appCervezas.error.ProductNotFoundException;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @GetMapping("/breweries")
    public List<Brewery> getAllBreweries() {
        return breweryService.getAllBreweries();
    }

    @GetMapping("/brewery/{id}")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable Long id) {
        try {
            Brewery brewery = breweryService.getBreweryById(id);
            return ResponseEntity.ok(brewery);
        } catch (ProductNotFoundException ex) {
            // Si la excepción es lanzada, devolvemos un HttpStatus.NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
