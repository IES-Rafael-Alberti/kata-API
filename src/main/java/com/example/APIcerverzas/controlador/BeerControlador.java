package com.example.APIcerverzas.controlador;

import com.example.APIcerverzas.error.BeerNotFound;
import com.example.APIcerverzas.modelo.Beer;
import com.example.APIcerverzas.repos.BeerRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/beer")
public class BeerControlador {
    private final BeerRepositorio beerRepositorio;
    public BeerControlador(BeerRepositorio beerRepositorio) {
        this.beerRepositorio = beerRepositorio;
    }

    /**
     * @param page Numero de pagina
     * @param size Tamaño de la pagina
     * @return Obtiene todas las cervezas paginadas
     */
    @GetMapping("/beers")
    public Page<Beer> getBeers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Beer> beerPage = beerRepositorio.findAll((pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(beerPage).getBody();
    }

    /**
     * @param beer Cerveza
     * @return Postea la cerveza
     */
    @PostMapping("/beer")
    public ResponseEntity<Beer> postBeer(@Valid @RequestBody Beer beer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beerRepositorio.save(beer));
    }

    /**
     * @param id Id de la cerveza a obtener
     * @return Obtiene la cerveza con ese id
     */
    @GetMapping("/beer/{id}")
    public ResponseEntity<Optional<Beer>> getBeerById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(beerRepositorio.findById(id));
    }

    /**
     * @param id Id de la cerveza a borrar
     * @return Borra la cerveza con ese id
     */
    @DeleteMapping("/beer/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        return beerRepositorio.findById(id)
                .map(producto -> {
                    beerRepositorio.delete(producto);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new BeerNotFound(id));
    }

    /**
     * @param id Id de la cerveza a modificar
     * @return Modifica la cerveza con ese id
     */
    @PutMapping("/beer/{id}")
    public Beer updateBeer(@PathVariable Long id, @Valid @RequestBody Beer beer) {
        return beerRepositorio.findById(id)
                .map(existingBeer -> {
                    existingBeer.setId(beer.getId());
                    existingBeer.setBrewery_id(beer.getBrewery_id());
                    existingBeer.setName(beer.getName());
                    existingBeer.setCat_id(beer.getCat_id());
                    existingBeer.setStyle_id(beer.getStyle_id());
                    existingBeer.setAbv(beer.getAbv());
                    existingBeer.setIbu(beer.getIbu());
                    existingBeer.setSrm(beer.getSrm());
                    existingBeer.setUpc(beer.getUpc());
                    existingBeer.setFilepath(beer.getFilepath());
                    existingBeer.setDescript(beer.getDescript());
                    existingBeer.setAdd_user(beer.getAdd_user());
                    existingBeer.setLast_mod(beer.getLast_mod());
                    return ResponseEntity.status(HttpStatus.OK).body(beerRepositorio.save(existingBeer)).getBody();
                })
                .orElseThrow(() ->  new BeerNotFound(id));
    }
}
