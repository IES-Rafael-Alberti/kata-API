package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.services.BreweryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        Brewery brewery1 = crearBrewery(1L, "Brewery One", "123 Main Street", "Suite 100",
                "Cityville", "Stateville", "12345", "Countryland", "555-1234", "", "", "A fantastic brewery in Cityville.", 1);

        Brewery brewery2 = crearBrewery(2L, "Brewery Two", "456 Oak Avenue", "Floor 2",
                "Townton", "Stateland", "67890", "Countryville", "555-5678", "", "", "A unique brewery in Townton.", 2);


        List<BreweryDTO> mockBreweries = Arrays.asList(new BreweryDTO(brewery1), new BreweryDTO(brewery2));
        when(breweryService.getAllBreweries()).thenReturn(mockBreweries);


        // Llamar al método del controlador
        List<BreweryDTO> result = breweryController.getAllBreweries();

        // Verificar el resultado
        assertEquals(mockBreweries.size(), result.size());

        // Verificar que se llamó al servicio
        verify(breweryService, times(1)).getAllBreweries();
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





}
