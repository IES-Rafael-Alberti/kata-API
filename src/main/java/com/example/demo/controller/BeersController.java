package com.example.demo.controller;

import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import com.example.demo.servicies.BeerService;
import error.BeerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for managing beer-related operations.
 */
@RestController
@RequiredArgsConstructor
public class BeersController {

    private final BeerService serviceBeer;

    /**
     * Retrieves a paginated list of beers.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceBeerDTO containing beer details.
     */
    @GetMapping("/beers")
    public Page<ServiceBeerDTO> getBeers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return serviceBeer.getBeers(page,size);
    }

    /**
     * Creates a new beer.
     *
     * @param beer ServiceBeerDTO representing the beer to be created.
     * @return ResponseEntity with status 201 (Created) and the created ServiceBeerDTO.
     */
    @PostMapping("/beer")
    public ResponseEntity<ServiceBeerDTO> createBeer(@RequestBody ServiceBeerDTO beer){
        return serviceBeer.postBeer(beer);
    }
    /**
     * Retrieves details of a specific beer by its ID.
     *
     * @param id ID of the beer to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceBeerDTO if the beer exists.
     *         ResponseEntity with status 404 (Not Found) if the beer does not exist.
     */
    @GetMapping("/beer/{id}")
    public ResponseEntity<ServiceBeerDTO> getBeer(@PathVariable Long id) {
        return serviceBeer.getBeer(id);
    }

    /**
     * Deletes a specific beer by its ID.
     *
     * @param id ID of the beer to delete.
     * @return ResponseEntity with status 204 (No Content) if the deletion is successful.
     *         ResponseEntity with status 404 (Not Found) if the beer does not exist.
     */
    @DeleteMapping("/beer/{id}")
    public ResponseEntity<Beer> deleteBeer(@PathVariable Long id){
        return serviceBeer.removeBeer(id);
    }

    /**
     * Updates the details of a specific beer by its ID.
     *
     * @param id ID of the beer to update.
     * @param updatedBeer ServiceBeerDTO representing the updated beer details.
     * @return ServiceBeerDTO with the updated details.
     * @throws BeerNotFound Exception thrown if the beer with the given ID is not found (status 404).
     */
    @PutMapping("/beer/{id}")
    public ServiceBeerDTO updateBeer(@PathVariable Long id, @RequestBody ServiceBeerDTO updatedBeer) {
        return serviceBeer.updateBeer(id, updatedBeer);
    }
}
