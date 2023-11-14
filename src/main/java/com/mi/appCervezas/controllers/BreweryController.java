package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.error.BreweryNotFoundException;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @GetMapping("/breweries")
    public List<BreweryDTO> getAllBreweries() {
        List<BreweryDTO> breweryDTOs = new ArrayList<>();
        List<BreweryDTO> breweries = breweryService.getAllBreweries();

        for (BreweryDTO brewery : breweries) {
            breweryDTOs.add(new BreweryDTO(brewery));
        }

        return breweryDTOs;
    }


    @GetMapping("/brewery/{id}")
    public ResponseEntity<BreweryDTO> getBreweryById(@PathVariable Long id) {
        try {
            Brewery brewery = breweryService.getBreweryById(id);

            if (brewery != null) {
                BreweryDTO breweryDTO = new BreweryDTO(brewery);
                return ResponseEntity.ok(breweryDTO);
            } else {
                // Maneja el caso donde la cervecería no fue encontrada
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (BreweryNotFoundException ex) {
            // Maneja la excepción si sucede
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
