package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.error.BeerNotFoundException;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.services.BeerService;
import com.mi.appCervezas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class BeerController {


    @Autowired
    private BeerService beerService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/beers")
    public List<BeerDTO> getAllBeers() {
        List<Beer> beers = beerService.getAllBeers();
        return beers.stream()
                .map(BeerDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/beer")
    public ResponseEntity<?> addBeer(@RequestBody BeerDTO beerDTO) {
        try {
            // Validación adicional
            validateBeerDTO(beerDTO);

            // Registros detallados
            System.out.println("BeerDTO recibido: " + beerDTO);

            // Obtener la categoría por ID utilizando el CategoryService
            Category category = categoryService.getCategoryById(beerDTO.getCategoryId());
            if (category == null) {
                throw new IllegalArgumentException("No se encontró una categoría con el ID proporcionado: " + beerDTO.getCategoryId());
            }

            // Configurar la categoría en la cerveza
            Beer beer = beerDTO.toBeer();
            beer.setCategory(category);
            beer.setCategoryId(category.getId()); // Establecer el categoryId

            System.out.println("Beer creado: " + beer);

            // Comprobar que breweryId no sea nulo
            if (beer.getBreweryId() == null) {
                throw new IllegalArgumentException("breweryId en Beer no puede ser nulo");
            }

            Beer savedBeer = beerService.addBeer(beer);
            BeerDTO savedBeerDTO = new BeerDTO(savedBeer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBeerDTO);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error en el servidor");
        }
    }

    private void validateBeerDTO(BeerDTO beerDTO) {
        if (beerDTO.getBreweryId() == null) {
            throw new IllegalArgumentException("breweryId no puede ser nulo");
        }
        if (beerDTO.getName() == null || beerDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("name no puede ser nulo o vacío");
        }
        if (beerDTO.getAbv() <= 0 || beerDTO.getAbv() > 100) {
            throw new IllegalArgumentException("abv debe estar entre 0 y 100");
        }
        if (beerDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("categoryId no puede ser nulo");
        }
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
            Beer beer = beerDTO.toBeer();
            Beer updatedBeer = beerService.updateBeer(id, beer);
            BeerDTO updatedBeerDTO = new BeerDTO(updatedBeer);
            return ResponseEntity.ok(updatedBeerDTO);
        } catch (BeerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
