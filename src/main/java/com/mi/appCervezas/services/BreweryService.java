package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BreweryService {

    private final BreweryRepository breweryRepository;

    /**
     * Constructor de la clase BreweryService.
     *
     * @param breweryRepository Repositorio de cervecerías utilizado para acceder a la base de datos.
     */
    @Autowired
    public BreweryService(BreweryRepository breweryRepository) {
        // Se asigna el repositorio de cervecerías proporcionado al atributo de la clase
        this.breweryRepository = breweryRepository;
    }

    /**
     * Recupera todas las cervecerías disponibles paginadas.
     *
     * @param pageable Objeto Pageable que define la información de paginación y ordenación.
     * @return Página de objetos Brewery que representa las cervecerías recuperadas.
     */
    public Page<Brewery> getAllBreweries(Pageable pageable) {
        return breweryRepository.findAll(pageable);
    }


    /**
     * Recupera una cervecería por su ID y la devuelve como un objeto DTO.
     *
     * @param id El ID de la cervecería que se va a recuperar.
     * @return Objeto BreweryDTO que representa la cervecería recuperada, o null si no se encuentra.
     */
    public BreweryDTO getBreweryById(Long id) {
        // Utiliza el método findById del repositorio de cervecerías para obtener la cervecería por su ID
        Brewery brewery = breweryRepository.findById(id).orElse(null);

        // Devuelve un objeto BreweryDTO si la cervecería se encuentra, de lo contrario, devuelve null
        return (brewery != null) ? new BreweryDTO(brewery) : null;
    }


}
