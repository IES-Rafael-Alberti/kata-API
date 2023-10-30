package com.mi.appCervezas.controllers;

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
        Beer beer = beerService.getBeerById(id);
        return ResponseEntity.ok(beer);
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @RequestBody Beer beer) {
        Beer updatedBeer = beerService.updateBeer(id, beer);
        return ResponseEntity.ok(updatedBeer);
    }
}
