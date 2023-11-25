package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BreweryControllerTest {

    @Mock
    private BreweryService breweryService;

    @InjectMocks
    private BreweryController breweryController;

    @Test
    void getAllBreweries() {
        // Configurar el comportamiento simulado del servicio
        Page<Brewery> mockBreweries = new PageImpl<>(Arrays.asList(
                crearBrewery(1L, "Brewery One", "123 Main Street", "Suite 100",
                        "Cityville", "Stateville", "12345", "Countryland", "555-1234", "", "", "A fantastic brewery in Cityville.", 1),
                crearBrewery(2L, "Brewery Two", "456 Oak Avenue", "Floor 2",
                        "Townton", "Stateland", "67890", "Countryville", "555-5678", "", "", "A unique brewery in Townton.", 2)));

        when(breweryService.getAllBreweries(any(Pageable.class))).thenReturn(mockBreweries);

        // Llamar al método del controlador
        ResponseEntity<Page<BreweryDTO>> result = breweryController.getAllBreweries(0, 5);

        // Verificar el resultado
        assertEquals(mockBreweries.getTotalElements(), Objects.requireNonNull(result.getBody()).getTotalElements());

        // Verificar que se llamó al servicio con los parámetros de paginación correctos
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(breweryService, times(1)).getAllBreweries(pageableCaptor.capture());

        Pageable pageableUsed = pageableCaptor.getValue();
        assertEquals(0, pageableUsed.getPageNumber());
        assertEquals(5, pageableUsed.getPageSize());
    }


    public static Brewery crearBrewery(Long id, String name, String address1, String address2, String city,
                                        String state, String code, String country, String phone, String website,
                                        String filepath, String descript, int add_user) {

        Brewery brewery = new Brewery();
        brewery.setId(id);
        brewery.setName(name);
        brewery.setAddress1(address1);
        brewery.setAddress2(address2);
        brewery.setCity(city);
        brewery.setState(state);
        brewery.setCode(code);
        brewery.setCountry(country);
        brewery.setPhone(phone);
        brewery.setWebsite(website);
        brewery.setFilepath(filepath);
        brewery.setDescript(descript);
        brewery.setAdd_user(add_user);
        brewery.setLast_mod(new Date());

        return brewery;
    }


    @Test
    void getBreweryById() {
        // Configurar datos simulados
        Long breweryId = 1L;

        // Crea un objeto Brewery simulado
        Brewery simulatedBrewery = new Brewery();
        // Configura los atributos del Brewery simulado según sea necesario

        // Crea un objeto BreweryDTO a partir del Brewery simulado
        BreweryDTO breweryDTO = new BreweryDTO(simulatedBrewery);

        ResponseEntity<BreweryDTO> expectedResponse = ResponseEntity.ok(breweryDTO);

        // Configurar comportamiento simulado del servicio
        BreweryService breweryService = mock(BreweryService.class);

        // Corrige el comportamiento simulado para que devuelva un BreweryDTO
        when(breweryService.getBreweryById(breweryId)).thenReturn(breweryDTO);

        // Instanciar el controlador con el servicio simulado
        BreweryController breweryController = new BreweryController(breweryService);

        // Llamar al método del controlador
        ResponseEntity<BreweryDTO> result = breweryController.getBreweryById(breweryId);

        // Verificar el resultado
        assertEquals(expectedResponse.getStatusCode(), result.getStatusCode());
        assertEquals(expectedResponse.getBody(), result.getBody());

        // Verificar que se llamó al servicio
        verify(breweryService, times(1)).getBreweryById(breweryId);
    }

    @Test
    void getNonexistentBreweryById() {
        // Configurar datos simulados
        Long nonexistentBreweryId = 5000L;

        // Configurar comportamiento simulado del servicio para devolver null
        when(breweryService.getBreweryById(nonexistentBreweryId)).thenReturn(null);

        // Instanciar el controlador con el servicio simulado
        BreweryController breweryController = new BreweryController(breweryService);

        // Llamar al método del controlador
        ResponseEntity<BreweryDTO> result = breweryController.getBreweryById(nonexistentBreweryId);

        // Verificar el resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode()); // Verificar que el código de estado es 404 (Not Found)
        assertNull(result.getBody()); // Verificar que el cuerpo de la respuesta es null

        // Verificar que se llamó al servicio
        verify(breweryService, times(1)).getBreweryById(nonexistentBreweryId);
    }


}
