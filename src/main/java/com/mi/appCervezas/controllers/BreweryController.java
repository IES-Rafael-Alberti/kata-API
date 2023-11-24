package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.error.BreweryNotFoundException;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    // Constructor que acepta el servicio como parámetro
    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }


    @GetMapping("/breweries")
    public ResponseEntity<Page<BreweryDTO>> getAllBreweries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Brewery> breweryPage = breweryService.getAllBreweries(pageable);

        List<BreweryDTO> breweryDTOList = breweryPage.getContent().stream()
                .map(BreweryDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(breweryDTOList, pageable, breweryPage.getTotalElements()));
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
