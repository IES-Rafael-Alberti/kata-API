package com.example.kataAPI.controller;

import com.example.kataAPI.errors.custom_exceptions.Not_found_beer;
import com.example.kataAPI.errors.custom_exceptions.Not_found_exception;
import com.example.kataAPI.model.Style;
import com.example.kataAPI.repo.repo_style;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller_Styles {

    private final com.example.kataAPI.repo.repo_style repo_style;

    public Controller_Styles(repo_style repo_style) {
        this.repo_style = repo_style;
    }

    @GetMapping("/styles")// Listar todos los estilos -style- GET
    @ResponseBody
    public ResponseEntity<List<Style>> get_all_styles () {
        List<Style> all_styles = repo_style.findAll();
        if (!all_styles.isEmpty()) {
            return ResponseEntity.ok(all_styles);
        }
        else {
            throw new Not_found_exception("No data for categories were found");
        }
    }

    @GetMapping("/style/{id}")// Mostrar el estilo -style- {id}	GET
    @ResponseBody
    public ResponseEntity<Style> get_style(@PathVariable Integer id) {
        Style style_found = repo_style.findById(id)
                .orElseThrow(() -> new Not_found_exception("The style with id "+ id + "wasn't found"));
    return ResponseEntity.ok(style_found);
    }

}
