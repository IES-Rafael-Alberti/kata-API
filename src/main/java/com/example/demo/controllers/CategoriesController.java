package com.example.demo.controllers;

import com.example.demo.models.Categories;
import com.example.demo.repositories.CategoriesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoriesController {

    private final CategoriesRepository categoriesRepository;

    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/categories")
    public Page<Categories> getCategories(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categories> categoriesPage = categoriesRepository.findAll(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesPage).getBody();
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
    }

}
