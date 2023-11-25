package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

        List<Category> mockCategories = Arrays.asList(category1, category2);

        // Ajustar el retorno simulado para que sea un Page<Category>
        Page<Category> mockCategoryPage = new PageImpl<>(mockCategories);

        // Utilizar any() para simular cualquier Pageable
        when(categoryService.getAllCategories(any(Pageable.class))).thenReturn(mockCategoryPage);

        // Llamar al método del controlador
        ResponseEntity<Page<CategoryDTO>> result = categoryController.getAllCategories(0, 5);


        // Verificar el resultado
        assertEquals(mockCategories.size(), Objects.requireNonNull(result.getBody()).getContent().size());

        // Verificar que se llamó al servicio
        verify(categoryService, times(1)).getAllCategories(any(Pageable.class));
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
