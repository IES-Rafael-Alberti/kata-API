package com.example.kataAPI.controller;

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
        return repo_beer.findAll();
    }
    @GetMapping("/beer/{id}") // Mostrar la cerveza con el id {id}	GET
    @ResponseBody
    public Beer get_beer(@PathVariable Integer id){
        return repo_beer.findById(id).orElse(null);
    }

    @PostMapping("/beer")
    @ResponseBody
    public ResponseEntity<Beer> add_beer(@Valid @RequestBody Beer beer) {
        Beer beer_exist = repo_beer.findByName(beer.getName());
        if (beer_exist == null ) {
            repo_beer.save(beer);
            return ResponseEntity.ok(beer);
        }
        throw new Beer_exist(); implementar

    }

    @DeleteMapping("/beer/{id}") // Eliminar una cerveza DELETE
    @ResponseBody
    public ResponseEntity<Beer> delete_beer (@PathVariable Integer id) {
        return repo_beer.findById(id)
                .map(beer -> {
                    repo_beer.delete(beer);
                    return ResponseEntity.ok().body("Producto deleted");
                })
                .orElseThrow(() -> new ResourceNotFoundException("Beer not found with id " + id));
    }

    @PutMapping("/beer/{id}")  // Modificar parcialmente una cerveza PUT o PATCH
    @ResponseBody
    public ResponseEntity<Beer> update_beer (@PathVariable Integer id @Valid @RequestBody Beer beer) {
        return repo_beer.findById(id)
                .map(existing_beer -> {
                    existing_beer.setName(existing_beer.getName());
                    existing_beer.setAbv(existing_beer.getAbv());
                    existing_beer.setIbu(existing_beer.getIbu());
                    existing_beer.setSrm(existing_beer.getSrm());
                    existing_beer.setUpc(existing_beer.getUpc());
                    existing_beer.setFilepath(existing_beer.getFilepath());
                    existing_beer.setDescript(existing_beer.getDescript());
                    return repo_beer.save(existing_beer);
                })
                .orElseThrow(() -> new NotFoundException("Beer not found with id " + id));
    }

}
