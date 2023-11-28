package com.example.kataAPI.controller;

import com.example.kataAPI.errors.custom_exceptions.Not_found_exception;
import com.example.kataAPI.model.Brewery;
import com.example.kataAPI.repo.repo_brewery;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Brewery>> get_all_breweries() {
        List<Brewery> all_breweries= repo_brewery.findAll();
      if (!all_breweries.isEmpty()) {
          return ResponseEntity.ok(all_breweries);
      }
      else {
          throw new Not_found_exception("No data for breweries were found");
      }
    }

    @GetMapping("/brewerie/{id}")// Mostrar la cervecera {id}	GET
    @ResponseBody
    public ResponseEntity<Brewery> get_brewery(@PathVariable Integer id) {
        Brewery brewery = repo_brewery.findById(id)
                            .orElseThrow(() -> new Not_found_exception("The brewery with id "+ id + " wasn't found"));
        return ResponseEntity.ok(brewery);
    }
}
