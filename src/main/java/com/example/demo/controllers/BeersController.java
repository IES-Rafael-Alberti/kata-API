package com.example.demo.controllers;

/*
/beer	Añadir una cerveza	POST
/beer/{id}	Modificar una cerveza	PUT
*/

import com.example.demo.dto.CreateBeerDTO;
import com.example.demo.dto.UpdateBeerDTO;
import com.example.demo.models.Beer;
import com.example.demo.repositories.BeerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class BeersController {

    private final BeerRepository beerRepository;

    public BeersController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @GetMapping("/beers")
    public Page<Beer> getBeers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Beer> beerPage = beerRepository.findAll(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(beerPage).getBody();
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        Beer beer = beerRepository.findById(id).orElse(null);
        if (beer != null) {
            return new ResponseEntity<>(beer, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Beer not found with id " + id);
        }
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable Long id) {
        return beerRepository.findById(id)
                .map(beer -> {
                    beerRepository.delete(beer);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Beer not found with id " + id));
    }

    @PostMapping("/beer")
    public ResponseEntity<?> addBeer(@Valid @RequestBody CreateBeerDTO beerDTO) {
        Beer beer = new Beer();
        beer.setName(beerDTO.getName());
        beer.setBrewery_id(beerDTO.getBrewery_id());
        beer.setCat_id(beerDTO.getCat_id());
        beer.setStyle_id(beerDTO.getStyle_id());
        beer.setAbv(beerDTO.getAbv());
        beer.setIbu(beerDTO.getIbu());
        beer.setSrm(beerDTO.getSrm());
        beer.setUpc(beerDTO.getUpc());
        beer.setFilepath(beerDTO.getFilepath());
        beer.setDescript(beerDTO.getDescript());
        beer.setAdd_user(beerDTO.getAdd_user());
        beer.setLast_mod(new Date());
        Beer beerAdded = beerRepository.save(beer);
        return new ResponseEntity<>(beerAdded, HttpStatus.CREATED);
    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<?> updateBeer(@PathVariable Long id, @Valid @RequestBody UpdateBeerDTO beerDTO) {
        Beer beer = new Beer();
        beer.setId(id);
        beer.setName(beerDTO.getName());
        beer.setBrewery_id(beerDTO.getBrewery_id());
        beer.setCat_id(beerDTO.getCat_id());
        beer.setStyle_id(beerDTO.getStyle_id());
        beer.setAbv(beerDTO.getAbv());
        beer.setIbu(beerDTO.getIbu());
        beer.setSrm(beerDTO.getSrm());
        beer.setUpc(beerDTO.getUpc());
        beer.setFilepath(beerDTO.getFilepath());
        beer.setDescript(beerDTO.getDescript());
        beer.setAdd_user(beerDTO.getAdd_user());
        beer.setLast_mod(new Date());
        Beer beerUpdated = beerRepository.save(beer);
        return new ResponseEntity<>(beerUpdated, HttpStatus.OK);
    }
}
