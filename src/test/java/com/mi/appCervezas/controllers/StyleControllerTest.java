package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
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
class StyleControllerTest {

    @Mock
    private StyleService styleService;

    @InjectMocks
    private StyleController styleController;

    @Test
    void getAllStyles() {
        // Configurar el comportamiento simulado del servicio
        Style style1 = crearStyle(1L, 1, "Style1", new Date());
        Style style2 = crearStyle(2L, 1, "Style2", new Date());
        List<StyleDTO> mockStyles = Arrays.asList(new StyleDTO(style1), new StyleDTO(style2));
        when(styleService.getAllStyles()).thenReturn(mockStyles);

        // Llamar al método del controlador
        List<StyleDTO> result = styleController.getAllStyles();

        // Verificar el resultado
        assertEquals(mockStyles.size(), result.size());

        // Verificar que se llamó al servicio
        verify(styleService, times(1)).getAllStyles();
    }

    public static Style crearStyle(Long id, Integer cat_id, String style_name, Date last_mod) {
        Style style = new Style();
        style.setId(id);
        style.setCat_id(cat_id);
        style.setStyle_name(style_name);
        style.setLast_mod(last_mod);
        return style;
    }

    @Test
    void getStyleById() {
        // Configurar datos simulados
        Long styleId = 1L;
        Style mockStyle = crearStyle(styleId, 1, "Style1", new Date());
        StyleDTO mockStyleDTO = new StyleDTO(mockStyle);

        // Configurar comportamiento simulado del servicio
        when(styleService.getStyleById(styleId)).thenReturn(mockStyleDTO);

        // Llamar al método del controlador
        ResponseEntity<StyleDTO> result = styleController.getStyleById(styleId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockStyleDTO, result.getBody());

        // Verificar que se llamó al servicio
        verify(styleService, times(1)).getStyleById(styleId);
    }
}
