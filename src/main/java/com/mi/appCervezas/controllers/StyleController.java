package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class StyleController {

    @Autowired
    private StyleService styleService;

    /**
     * Maneja las solicitudes HTTP GET para recuperar una lista paginada de estilos.
     *
     * @param page   Número de página solicitado (por defecto: 0).
     * @param size   Tamaño de la página solicitado (por defecto: 5).
     * @param sortBy Nombre del campo por el cual se ordenará la consulta (por defecto: "id").
     * @return ResponseEntity<Page<StyleDTO>> con la lista paginada de StyleDTO y detalles de paginación.
     */
    @GetMapping("/styles")
    public ResponseEntity<Page<StyleDTO>> getAllStyles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        // Creación de objeto Pageable para gestionar la paginación y ordenación
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        // Llamada al servicio para obtener una página de estilos
        Page<Style> stylePage = styleService.getAllStyles(pageable);

        // Mapeo de las entidades Style a DTO
        List<StyleDTO> styleDTOList = stylePage.getContent().stream()
                .map(StyleDTO::new)
                .collect(Collectors.toList());

        /*
         * Se devuelve una respuesta HTTP 200 (OK) con una instancia de PageImpl que contiene:
         * La lista de objetos StyleDTO, la información de paginación y el total de elementos
         * (número total de estilos)
         */
        return ResponseEntity.ok(new PageImpl<>(styleDTOList, pageable, stylePage.getTotalElements()));
    }


    /**
     * Maneja las solicitudes HTTP GET para recuperar un estilo por su ID.
     *
     * @param id El ID del estilo que se va a recuperar, obtenido del path de la URL.
     * @return ResponseEntity<StyleDTO> con el estilo DTO y el código de estado correspondiente.
     */
    @GetMapping("/style/{id}")
    public ResponseEntity<StyleDTO> getStyleById(@PathVariable Long id) {
        try {
            // Intenta obtener el estilo por su ID a través del servicio
            Style style = styleService.getStyleById(id);
            if (style != null) {
                // Si el estilo existe, construye un DTO y devuelve una respuesta OK
                StyleDTO styleDTO = new StyleDTO(style);
                return ResponseEntity.ok(styleDTO);
            } else {
                // Si el estilo no se encuentra, devuelve una respuesta 404 (Not Found)
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Maneja cualquier excepción no controlada y retorna una respuesta 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
