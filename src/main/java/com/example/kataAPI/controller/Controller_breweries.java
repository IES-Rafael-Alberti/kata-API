package com.example.kataAPI.controller;

import com.example.kataAPI.model.Brewery;
import com.example.kataAPI.repo.repo_brewery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class Controller_breweries {

    private final com.example.kataAPI.repo.repo_brewery repo_brewery;

    public Controller_breweries(repo_brewery repo_brewery) {
        this.repo_brewery = repo_brewery;
    }
    @GetMapping("/breweries")// Listar todas las cerveceras	GET
    @ResponseBody
    public List<Brewery> get_all_breweries() { return repo_brewery.findAll(); }

    @GetMapping("/brewerie/{id}")// Mostrar la cervecera {id}	GET
    @ResponseBody
    public Brewery get_brewery(@PathVariable Integer id) {
        return repo_brewery.findById(id).orElse(null);
    }
}
