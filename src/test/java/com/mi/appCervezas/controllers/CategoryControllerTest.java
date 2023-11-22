package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.repositories.CategoryRepository;
import com.mi.appCervezas.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void getAllCategories() {
        // Configurar el comportamiento simulado del servicio
        Category category1 = crearCategory(1L, "Category1", new Date());
        Category category2 = crearCategory(2L, "Category2", new Date());

        List<CategoryDTO> mockCategories = Arrays.asList(new CategoryDTO(category1), new CategoryDTO(category2));
        when(categoryService.getAllCategories()).thenReturn(mockCategories);

        // Llamar al método del controlador
        List<CategoryDTO> result = categoryController.getAllCategories();

        // Verificar el resultado
        assertEquals(mockCategories.size(), result.size());

        // Verificar que se llamó al servicio
        verify(categoryService, times(1)).getAllCategories();
    }

    public static Category crearCategory(Long id, String cat_name, Date last_mod) {

        Category category = new Category();
        category.setId(id);
        category.setCat_name(cat_name);
        category.setLast_mod(last_mod);

        return category;
    }

    @Test
    void getCategoryById() {
        // Configurar datos simulados
        Long categoryId = 1L;
        Category mockCategory = crearCategory(categoryId, "Category1", new Date());
        CategoryDTO mockCategoryDTO = new CategoryDTO(mockCategory);

        // Configurar comportamiento simulado del servicio
        when(categoryService.getCategoryById(categoryId)).thenReturn(mockCategoryDTO);

        // Llamar al método del controlador
        ResponseEntity<CategoryDTO> result = (ResponseEntity<CategoryDTO>) categoryController.getCategoryById(categoryId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockCategoryDTO, result.getBody());

        // Verificar que se llamó al servicio
        verify(categoryService, times(1)).getCategoryById(categoryId);
    }

}
