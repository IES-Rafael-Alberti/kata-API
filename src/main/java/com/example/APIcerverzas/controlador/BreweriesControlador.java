package com.example.APIcerverzas.controlador;

import com.example.APIcerverzas.modelo.Breweries;
import com.example.APIcerverzas.repos.BreweriesRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/breweries")
public class BreweriesControlador {
    private final BreweriesRepositorio breweriesRepositorio;
    public BreweriesControlador(BreweriesRepositorio breweriesRepositorio) {this.breweriesRepositorio = breweriesRepositorio;}

    /**
     * @param page Numero de pagina
     * @param size Tamaño de la pagina
     * @return Obtiene todas las cervecerías paginadas
     */
    @GetMapping("/breweries")
    public Page<Breweries> getBreweries(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Breweries> breweriesPage = breweriesRepositorio.findAll((pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(breweriesPage).getBody();
    }

    /**
     * @param id Id de la cervecería a obtener
     * @return Obtiene la cervecería con ese id
     */
    @GetMapping("/brewerie/{id}")
    public ResponseEntity<Optional<Breweries>> getBrewerieById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(breweriesRepositorio.findById(id));
    }
}
