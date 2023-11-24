package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.dto.CategoryDTO;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.services.BeerService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.mi.appCervezas.controllers.CategoryControllerTest.crearCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    @Mock
    CategoryService categoryService;

    @Mock
    private BeerService beerService;

    @InjectMocks
    private BeerController beerController;

    @Test
    void getAllBeers() {
        // Configurar el comportamiento simulado del servicio
        List<Beer> mockBeers = Arrays.asList(new Beer(), new Beer());
        Page<Beer> mockBeerPage = new PageImpl<>(mockBeers);

        when(beerService.getAllBeers(any(Pageable.class))).thenReturn(mockBeerPage);

        // Llamar al método del controlador
        ResponseEntity<Page<BeerDTO>> result = beerController.getAllBeers(0, 5);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockBeers.size(), Objects.requireNonNull(result.getBody()).getContent().size());

        // Verificar que se llamó al servicio con los parámetros de paginación correctos
        verify(beerService, times(1)).getAllBeers(any(Pageable.class));
    }

    @Test
    void addBeer() {
        // Configurar datos simulados
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setBrewery_id(1L);
        beerDTO.setName("Lager");
        beerDTO.setCat_id(1L);
        beerDTO.setAbv(6);
        beerDTO.setIbu(8);
        beerDTO.setSrm(10);
        beerDTO.setUpc(10);
        beerDTO.setFilepath("");
        beerDTO.setDescript("");
        beerDTO.setAdd_user(2);

        Date lastModDate = null;
        try {
            lastModDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2023-11-20T12:00:00.000+00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        beerDTO.setLast_mod(lastModDate);

        // Configurar comportamiento simulado del servicio de categoría
        Category category = crearCategory(1L, "Category Prueba", new Date());
        CategoryDTO mockCategory = new CategoryDTO(category);

        when(categoryService.getCategoryById(1L)).thenReturn(mockCategory);

        // Configurar comportamiento simulado del servicio de cerveza
        when(beerService.addBeer(any())).thenReturn(new Beer());

        // Llamar al método del controlador
        ResponseEntity<?> result = beerController.addBeer(beerDTO);

        // Comprobar el resultado
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody()); // Comprobar que el cuerpo de la respuesta no es nulo

        // Comprobar que se llamó al servicio de cerveza
        verify(beerService, times(1)).addBeer(any());

        // Comprobar que se llamó al servicio de categoría con el ID correcto
        verify(categoryService, times(1)).getCategoryById(1L);
    }



    @Test
    void getBeerById() {

        // Configurar datos simulados
        Long beerId = 1L;
        Beer mockBeer = new Beer();
        BeerDTO expectedBeerDTO = new BeerDTO(mockBeer);

        // Configurar comportamiento simulado del servicio
        when(beerService.getBeerById(beerId)).thenReturn(mockBeer);

        // Llamar al método del controlador
        ResponseEntity<BeerDTO> result = beerController.getBeerById(beerId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedBeerDTO, result.getBody());

        // Verificar que se llamó al servicio
        verify(beerService, times(1)).getBeerById(beerId);
    }

    @Test
    void deleteBeer() {
        // Configurar datos simulados
        Long beerId = 1L;

        // Llamar al método del controlador
        ResponseEntity<Void> result = beerController.deleteBeer(beerId);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Verificar que se llamó al servicio
        verify(beerService, times(1)).deleteBeer(beerId);
    }

    @Test
    void updateBeer() {
        // Configurar datos simulados
        Long beerId = 1L;
        BeerDTO updatedBeerDTO = new BeerDTO();

        updatedBeerDTO.setBrewery_id(2L);
        updatedBeerDTO.setName("Classic");
        updatedBeerDTO.setCat_id(2L);
        updatedBeerDTO.setAbv(8);
        updatedBeerDTO.setIbu(10);
        updatedBeerDTO.setSrm(12);
        updatedBeerDTO.setUpc(12);
        updatedBeerDTO.setFilepath("");
        updatedBeerDTO.setDescript("");
        updatedBeerDTO.setAdd_user(2);

        // Configurar comportamiento simulado del servicio
        Beer updatedBeer = new Beer();
        updatedBeer.setName(updatedBeerDTO.getName());
        when(beerService.updateBeer(eq(beerId), any(Beer.class))).thenReturn(updatedBeer);

        // Llamar al método del controlador
        ResponseEntity<BeerDTO> result = beerController.updateBeer(beerId, updatedBeerDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedBeerDTO.getName(), Objects.requireNonNull(result.getBody()).getName());

        // Verificar que se llamó al servicio
        verify(beerService, times(1)).updateBeer(eq(beerId), any(Beer.class));
    }
}