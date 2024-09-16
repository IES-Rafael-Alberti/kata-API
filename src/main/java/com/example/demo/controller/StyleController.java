package com.example.demo.controller;


import com.example.demo.dto.converter.StyleDTOConverter;
import com.example.demo.dto.serviceDto.ServiceStyleDTO;
import com.example.demo.entities.Style;
import com.example.demo.error.StyleNotFound;
import com.example.demo.servicies.StyleService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller class for managing style-related operations.
 */
@RestController
@RequiredArgsConstructor
public class StyleController {


    private final StyleService styleService;
    private final StyleDTOConverter dtoConverter;

    /**
     * Retrieves a paginated list of styles.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceStyleDTO containing style details.
     */
    @GetMapping("/styles")
    public Page<ServiceStyleDTO> getStyles(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return styleService.getStyles(page, size);
    }

    /**
     * Retrieves details of a specific style by its ID.
     *
     * @param id ID of the style to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceStyleDTO if the style exists.
     *         ResponseEntity with status 404 (Not Found) if the style does not exist.
     */
    @GetMapping("/style/{id}")
    public ResponseEntity<ServiceStyleDTO> getStyle(@PathVariable Long id) {
        return styleService.getStyle(id);
    }
}
