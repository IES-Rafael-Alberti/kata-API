package com.example.demo.controller;

import com.example.demo.dto.converter.CategoryDTOConverter;
import com.example.demo.dto.serviceDto.ServiceCategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.error.CategoryNotFound;
import com.example.demo.servicies.CategoryService;
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
 * Controller class for managing category-related operations.
 */
@RestController
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryDTOConverter dtoConverter;

    /**
     * Retrieves a paginated list of categories.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceCategoryDTO containing category details.
     */
    @GetMapping("/categories")
    public Page<ServiceCategoryDTO> getCategories(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return categoryService.getCategories(page, size);
    }

    /**
     * Retrieves details of a specific category by its ID.
     *
     * @param id ID of the category to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceCategoryDTO if the category exists.
     *         ResponseEntity with status 404 (Not Found) if the category does not exist.
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<ServiceCategoryDTO> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

}
