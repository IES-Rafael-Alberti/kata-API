package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Recupera todas las categorías disponibles paginadas.
     *
     * @param pageable Objeto Pageable que define la información de paginación y ordenación.
     * @return Página de objetos Category que representa las categorías recuperadas.
     */
    public Page<Category> getAllCategories(Pageable pageable) {
        // Usa el método findAll del repositorio de categorías para obtener una página de categorías paginada
        return categoryRepository.findAll(pageable);
    }

    /**
     * Recupera una categoría por su ID y la devuelve como un objeto DTO.
     *
     * @param id El ID de la categoría que se va a recuperar.
     * @return Objeto CategoryDTO que representa la categoría recuperada, o null si no se encuentra.
     */
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        // Devuelve un objeto CategoryDTO si la categoría se encuentra, de lo contrario, devuelve null
        if (category != null) {
            return new CategoryDTO(category);
        }
        return null;
    }
}
