package com.mi.appCervezas.services;

import com.mi.appCervezas.repositories.CategoryRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return (Category) categoryRepository.findById(id).orElse(null);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    /*public Category updateCategory(Long id, Category newCategory) {
        Category existingCategory = (Category) categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(newCategory.getName());
            // ... (actualizar otros campos según sea necesario)
            return categoryRepository.save(existingCategory);
        }
        return null;
    }*/
}

