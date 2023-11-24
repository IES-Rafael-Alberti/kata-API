package com.example.kataAPI.controller;

import com.example.kataAPI.errors.custom_exceptions.Not_found_beer;
import com.example.kataAPI.errors.custom_exceptions.Not_found_exception;
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
        List<Category> all_categories = repo_category.findAll();
        if (!all_categories.isEmpty()){
            return repo_category.findAll();
        }
        else {
            throw new Not_found_exception("No data for categories were found");
        }
    }

    @GetMapping("/categorie/{id}") // Mostrar la categoría {id}	GET
    @ResponseBody
    public Category get_category(@PathVariable Integer id) {
        return repo_category.findById(id)
                            .orElseThrow(() -> new Not_found_exception("The category with id " + id + " wasn't found"));
    }
}//
