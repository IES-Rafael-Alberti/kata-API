package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.error.BreweryNotFoundException;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
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
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    // Constructor que acepta el servicio como parámetro
    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }


    /**
     * Maneja las solicitudes HTTP GET para recuperar una lista paginada de cervecerías.
     *
     * @param page Número de página solicitado (por defecto: 0).
     * @param size Tamaño de la página solicitado (por defecto: 5).
     * @return ResponseEntity<Page<BreweryDTO>> con la lista paginada de BreweryDTO y detalles de paginación.
     */
    @GetMapping("/breweries")
    public ResponseEntity<Page<BreweryDTO>> getAllBreweries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        // Crear un objeto Pageable para la paginación
        Pageable pageable = PageRequest.of(page, size);

        // Llamar al servicio para obtener una página de cervecerías
        Page<Brewery> breweryPage = breweryService.getAllBreweries(pageable);

        // Mapear las entidades Brewery a DTO
        List<BreweryDTO> breweryDTOList = breweryPage.getContent().stream()
                .map(BreweryDTO::new)
                .collect(Collectors.toList());

        // Crear una respuesta ResponseEntity con la lista paginada de BreweryDTO y detalles de paginación
        return ResponseEntity.ok(new PageImpl<>(breweryDTOList, pageable, breweryPage.getTotalElements()));
    }


    /**
     * Maneja las solicitudes HTTP GET para recuperar una cervecería por su ID.
     *
     * @param id El ID de la cervecería que se va a recuperar, obtenido del path de la URL.
     * @return ResponseEntity<BreweryDTO> con la cervecería DTO y el código de estado correspondiente.
     */
    @GetMapping("/brewery/{id}")
    public ResponseEntity<BreweryDTO> getBreweryById(@PathVariable Long id) {
        try {
            // Intenta obtener la cervecería del servicio utilizando el ID proporcionado
            Brewery brewery = breweryService.getBreweryById(id);

            // Si la cervecería existe, crea un DTO y devuelve una respuesta OK
            if (brewery != null) {
                BreweryDTO breweryDTO = new BreweryDTO(brewery);
                return ResponseEntity.ok(breweryDTO);
            } else {
                // Maneja el caso donde la cervecería no fue encontrada, devuelve 404 (Not Found)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (BreweryNotFoundException ex) {
            // Controla la excepción si ocurre durante la obtención de la cervecería, devuelve 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
