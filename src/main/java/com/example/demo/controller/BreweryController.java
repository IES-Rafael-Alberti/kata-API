package com.example.demo.controller;

import com.example.demo.dto.converter.BreweryDTOConverter;
import com.example.demo.dto.serviceDto.ServiceBreweryDTO;
import com.example.demo.entities.Breweries;
import com.example.demo.error.BreweryNotFound;
import com.example.demo.servicies.BreweriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repos.BreweryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller class for managing brewery-related operations.
 */
@RestController
@RequiredArgsConstructor
public class BreweryController {

    private final BreweriesService breweriesService;

    /**
     * Retrieves a paginated list of breweries.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceBreweryDTO containing brewery details.
     */
    @GetMapping("/breweries")
    public Page<ServiceBreweryDTO> getBreweryList(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return breweriesService.getBreweryList(page, size);
    }

    /**
     * Retrieves details of a specific brewery by its ID.
     *
     * @param id ID of the brewery to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceBreweryDTO if the brewery exists.
     *         ResponseEntity with status 404 (Not Found) if the brewery does not exist.
     */
    @GetMapping("/brewerie/{id}")
    public ResponseEntity<ServiceBreweryDTO> getBrewery(@PathVariable Long id) {
        return breweriesService.getBrewery(id);
    }

}
