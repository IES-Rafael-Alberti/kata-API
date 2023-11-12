package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.error.CategoryNotFoundException;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        List<CategoryDTO> categories = categoryService.getAllCategories();

        for (CategoryDTO category : categories) {
            categoryDTOs.add(new CategoryDTO(category));
        }

        return categoryDTOs;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new CategoryDTO(category));
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
