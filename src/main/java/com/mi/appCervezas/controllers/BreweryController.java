package com.mi.appCervezas.controllers;

import com.mi.appCervezas.models.BreweryModel;
import com.mi.appCervezas.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/breweries")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @GetMapping
    public List<BreweryModel> getAllBreweries() {
        return breweryService.getAllBreweries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreweryModel> getBreweryById(@PathVariable Long id) {
        BreweryModel brewery = breweryService.getBreweryById(id);
        return ResponseEntity.ok(brewery);
    }

    @PostMapping
    public ResponseEntity<BreweryModel> addBrewery(@RequestBody BreweryModel brewery) {
        BreweryModel newBrewery = breweryService.addBrewery(brewery);
        return ResponseEntity.ok(newBrewery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrewery(@PathVariable Long id) {
        breweryService.deleteBrewery(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreweryModel> updateBrewery(@PathVariable Long id, @RequestBody BreweryModel newBrewery) {
        BreweryModel updatedBrewery = breweryService.updateBrewery(id, newBrewery);
        return ResponseEntity.ok(updatedBrewery);
    }
}


