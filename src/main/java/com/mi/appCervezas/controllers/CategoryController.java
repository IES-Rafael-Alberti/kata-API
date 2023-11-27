package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Maneja las solicitudes HTTP GET para recuperar una lista paginada de categorías.
     *
     * @param page Número de página solicitado (por defecto: 0).
     * @param size Tamaño de la página solicitado (por defecto: 5).
     * @return ResponseEntity<Page<CategoryDTO>> con la lista paginada de CategoryDTO y detalles de paginación.
     */
    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDTO>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        // Creación de objeto Pageable para gestionar la paginación
        Pageable pageable = PageRequest.of(page, size);

        // Llamada al servicio para obtener una página de categorías
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);

        // Mapeo de las entidades Category a DTO
        List<CategoryDTO> categoryDTOList = categoryPage.getContent().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());

        /*
         * Se devuelve una respuesta HTTP 200 (OK) con una instancia de PageImpl que contiene:
         * La lista de objetos CategoryDTO, la información de paginación y el total de elementos
         * (número total de categorías)
         */
        return ResponseEntity.ok(new PageImpl<>(categoryDTOList, pageable, categoryPage.getTotalElements()));
    }


    /**
     * Maneja las solicitudes HTTP GET para recuperar una categoría por su ID.
     *
     * @param id El ID de la categoría que se va a recuperar, obtenido del path de la URL.
     * @return ResponseEntity<?> con la categoría DTO y el código de estado correspondiente.
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            // Intenta obtener la categoría DTO por su ID mediante el servicio
            CategoryDTO category = categoryService.getCategoryById(id);
            if (category != null) {
                // Si la categoría existe, devuelve una respuesta OK con la categoría DTO
                return ResponseEntity.ok(new CategoryDTO(category));
            } else {
                // Si la categoría no se encuentra, devuelve una respuesta 404 (Not Found)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            // Maneja cualquier excepción no controlada y devuelve una respuesta 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

}
