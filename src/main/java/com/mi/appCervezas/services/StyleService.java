package com.mi.appCervezas.services;

import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    /**
     * Constructor de la clase StyleService.
     *
     * @param styleRepository Repositorio de estilos utilizado para acceder a la base de datos.
     */
    @Autowired
    public StyleService(StyleRepository styleRepository) {
        // Se asigna el repositorio de estilos proporcionado al atributo de la clase
        this.styleRepository = styleRepository;
    }

    /**
     * Recupera todos los estilos disponibles paginados.
     *
     * @param pageable Objeto Pageable que define la información de paginación y ordenación.
     * @return Página de objetos Style que representa los estilos recuperados.
     */
    public Page<Style> getAllStyles(Pageable pageable) {
        // Usa el método findAll del repositorio de estilos para obtener una página de estilos paginada
        return styleRepository.findAll(pageable);
    }

    /**
     * Recupera un estilo por su ID.
     *
     * @param id El ID del estilo que se va a recuperar.
     * @return Objeto Style que representa el estilo recuperado, o null si no se encuentra.
     */
    public Style getStyleById(Long id) {
        // Usa el método findById del repositorio de estilos para obtener el estilo por su ID
        return styleRepository.findById(id).orElse(null);
    }
}
