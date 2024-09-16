package com.example.APIcerverzas.controlador;

import com.example.APIcerverzas.modelo.Categories;
import com.example.APIcerverzas.repos.CategoriesRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoriesControlador {
    private final CategoriesRepositorio categoriesRepositorio;
    public CategoriesControlador(CategoriesRepositorio categoriesRepositorio) {this.categoriesRepositorio = categoriesRepositorio;}

    /**
     * @param page Numero de pagina
     * @param size Tamaño de la pagina
     * @return Obtiene todas las categorías paginadas
     */
    @GetMapping("/categories")
    public Page<Categories> getCategories(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categories> categoriesPage = categoriesRepositorio.findAll((pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(categoriesPage).getBody();
    }

    /**
     * @param id Id de la categoría a obtener
     * @return Obtiene la categoría con ese id
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Categories>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriesRepositorio.findById(id));
    }
}
