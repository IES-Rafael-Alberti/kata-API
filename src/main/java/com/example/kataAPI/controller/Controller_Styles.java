package com.example.kataAPI.controller;

import com.example.kataAPI.model.Style;
import com.example.kataAPI.repo.repo_style;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller_Styles {

    private final com.example.kataAPI.repo.repo_style repo_style;

    public Controller_Styles(repo_style repo_style) {
        this.repo_style = repo_style;
    }

    @GetMapping("/styles")// Listar todos los estilos -style-	GET
    @ResponseBody
    public List<Style> get_all_styles () {
        return repo_style.findAll();
    }

    @GetMapping("/style/{id}")// Mostrar el estilo -style- {id}	GET
    @ResponseBody
    public Style get_style(@PathVariable Integer id) {
        return repo_style.findById(id).orElse(null);
    }

}
