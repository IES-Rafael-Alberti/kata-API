package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.services.BeerService;
import com.mi.appCervezas.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    @Mock
    private BeerService beerService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BeerController beerController;

    @Test
    void getAllBeers() {

        // Configurar el comportamiento simulado del servicio
        List<Beer> mockBeers = Arrays.asList(new Beer(), new Beer());
        when(beerService.getAllBeers()).thenReturn(mockBeers);

        // Llamar al método del controlador
        List<BeerDTO> result = beerController.getAllBeers();

        // Verificar el resultado
        assertEquals(mockBeers.size(), result.size());

        // Verificar que se llamó al servicio
        verify(beerService, times(1)).getAllBeers();
    }

    @Test
    void addBeer() {

        // Configurar datos simulados
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setBrewery_id(1L);
        beerDTO.setName("Lager");
        beerDTO.setCat_id(3L);
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

        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body("beer added");

        // Configurar comportamiento simulado del servicio
        when(beerService.addBeer(any())).thenReturn(new Beer());

        // Llamar al método del controlador
        ResponseEntity<?> result = beerController.addBeer(beerDTO);

        // Verificar el resultado
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());

        // Verificar que se llamó al servicio
        verify(beerService, times(1)).addBeer(any());

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