package com.example.kataAPI.controller;

import com.example.kataAPI.entity.Beer;
import com.example.kataAPI.repo.repo_beer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller_beers {

    private final com.example.kataAPI.repo.repo_beer repo_beer;

    public Controller_beers(repo_beer repo_beer) {
        this.repo_beer = repo_beer;
    }

    @GetMapping("/beers") // Muestra todas las cervezas	GET
    @ResponseBody
    public List<Beer> get_all_beers(){
        return repo_beer.findAll();
    }
    @GetMapping("/beer/{id}") // Mostrar la cerveza con el id {id}	GET
    @ResponseBody
    public Beer get_beer(@PathVariable Integer id){
        return repo_beer.findById(id).orElse(null);
    }
    /*
       @PostMapping("/beer") // Añadir una cerveza	POST
    public Beer add_beer(){
        //todo
    }
     */

   // @DeleteMapping("/beer/{id}") // Eliminar una cerveza DELETE
   // @PutMapping("/beer/{id}")  // Modificar parcialmente una cerveza PUT o PATCH


}
