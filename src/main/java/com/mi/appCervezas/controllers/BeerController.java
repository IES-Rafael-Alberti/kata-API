package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.error.BeerNotFoundException;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @GetMapping("/beers")
    public List<BeerDTO> getAllBeers() {
        List<BeerDTO> beerDTOs = new ArrayList<>();
        List<Beer> beers = beerService.getAllBeers();

        for (Beer beer : beers) {
            beerDTOs.add(new BeerDTO(beer));
        }

        return beerDTOs;
    }

    @PostMapping("/beer")
    public ResponseEntity<BeerDTO> addBeer(@RequestBody BeerDTO beerDTO) {
        BeerDTO savedBeerDTO = new BeerDTO(beerService.addBeer(beerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBeerDTO);
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable Long id) {
        try {
            BeerDTO beerDTO = new BeerDTO(beerService.getBeerById(id));
            return ResponseEntity.ok(beerDTO);
        } catch (BeerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable Long id, @RequestBody BeerDTO beerDTO) {
        try {
            BeerDTO updatedBeerDTO = new BeerDTO(beerService.updateBeer(id, beerDTO));
            return ResponseEntity.ok(updatedBeerDTO);
        } catch (BeerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
