package com.example.kataAPI.controller;

import com.example.kataAPI.errors.custom_exceptions.Beer_exist;
import com.example.kataAPI.errors.custom_exceptions.Not_found_beer;
import com.example.kataAPI.errors.custom_exceptions.Not_found_exception;
import com.example.kataAPI.model.Beer;
import com.example.kataAPI.repo.repo_beer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller_beers {

    private final com.example.kataAPI.repo.repo_beer repo_beer;

    public Controller_beers(repo_beer repo_beer) {
        this.repo_beer = repo_beer;
    }

    @GetMapping("/beers") // Muestra todas las cervezas	GET
    @ResponseBody
    public List<Beer> get_all_beers(){
        List <Beer> all_beers = repo_beer.findAll();
        if (!all_beers.isEmpty()) {
            return all_beers;
        }
        else  {
            throw new Not_found_exception("No data were found");
        }
    }

    @GetMapping("/beer/{id}") // Mostrar la cerveza por el id {id}	GET
    @ResponseBody
    public Beer get_beer(@PathVariable Integer id){
        return repo_beer.findById(id)
                .orElseThrow(()-> new Not_found_beer("Beer with id "+id+" didn´t found"));
    }

    @PostMapping("/beer")
    @ResponseBody
    public ResponseEntity<Beer> add_beer(@Valid @RequestBody Beer beer) {
        Beer beer_exist = repo_beer.findByName(beer.getName());
        if (beer_exist == null ) {
            repo_beer.save(beer);
            return ResponseEntity.ok(beer);
        }
        else {
            throw new Beer_exist("This beer already exist");

        }
    }

    @DeleteMapping("/beer/{id}") // Eliminar una cerveza DELETE
    @ResponseBody
    public ResponseEntity<String> delete_beer (@PathVariable Integer id) {
        return repo_beer.findById(id)
                .map(beer -> {
                    repo_beer.delete(beer);
                    return ResponseEntity.ok().body("Producto deleted");
                })
                .orElseThrow(()-> new Not_found_beer("Beer with id "+id+" didn´t found"));
    }

    @PutMapping("/beer/{id}")  // Modificar parcialmente una cerveza PUT o PATCH
    @ResponseBody
    public ResponseEntity<Beer> update_beer (@PathVariable Integer id, @Valid @RequestBody Beer beer) {
        return repo_beer.findById(id).map(existing_beer -> {
                    existing_beer.setName(beer.getName());
                    existing_beer.setDescript(beer.getDescript());
                    repo_beer.save(existing_beer);
                    return ResponseEntity.ok(existing_beer);
                })
                .orElseThrow(()-> new Not_found_beer("Beer with id "+id+" didn´t found"));
    }

}
