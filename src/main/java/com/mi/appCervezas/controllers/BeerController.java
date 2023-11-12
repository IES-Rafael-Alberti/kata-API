package com.mi.appCervezas.controllers;

import com.mi.appCervezas.error.BeerNotFoundException;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @GetMapping("/beers")
    public List<Beer> getAllBeers() {
        return beerService.getAllBeers();
    }

    @PostMapping("/beer")
    public ResponseEntity<Beer> addBeer(@RequestBody Beer beer) {
        Beer savedBeer = beerService.addBeer(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBeer);
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        try {
            Beer beer = beerService.getBeerById(id);
            return ResponseEntity.ok(beer);
        } catch (BeerNotFoundException ex) {
            // Si la excepción es lanzada, devolvemos un HttpStatus.NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @RequestBody Beer beer) {
        try {
            Beer updatedBeer = beerService.updateBeer(id, beer);
            return ResponseEntity.ok(updatedBeer);
        } catch (BeerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
