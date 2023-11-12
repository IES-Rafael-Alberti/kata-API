package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            categoryDTOs.add(new CategoryDTO(category));
        }

        return categoryDTOs;
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            return new CategoryDTO(category);
        }
        return null;
    }
}
