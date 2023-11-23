package com.example.kataAPI.controller;

import com.example.kataAPI.model.Category;
import com.example.kataAPI.repo.repo_category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller_categories {

    private final com.example.kataAPI.repo.repo_category repo_category;

    public Controller_categories(repo_category repo_category) {
        this.repo_category = repo_category;
    }

    @GetMapping("/categories") // Listar todas las categorías GET
    @ResponseBody
    public List<Category> get_all_categories() {
        return repo_category.findAll();
    }
    @GetMapping("/categorie/{id}") // Mostrar la categoría {id}	GET
    @ResponseBody
    public Category get_category(@PathVariable Integer id) {
        return repo_category.findById(id).orElse(null);
    }
}//
