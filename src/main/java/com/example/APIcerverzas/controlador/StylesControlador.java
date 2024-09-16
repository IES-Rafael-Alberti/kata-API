package com.example.APIcerverzas.controlador;

import com.example.APIcerverzas.modelo.Styles;
import com.example.APIcerverzas.repos.StylesRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/styles")
public class StylesControlador {
    private final StylesRepositorio stylesRepositorio;
    public StylesControlador(StylesRepositorio stylesRepositorio) {this.stylesRepositorio = stylesRepositorio;}

    /**
     * @param page Numero de pagina
     * @param size Tamaño de la pagina
     * @return Obtiene todos los estilos paginados
     */
    @GetMapping("/styles")
    public Page<Styles> getStyles(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Styles> stylesPage = stylesRepositorio.findAll((pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(stylesPage).getBody();
    }

    /**
     * @param id Id del estilo a obtener
     * @return Obtiene el estilo con ese id
     */
    @GetMapping("/style/{id}")
    public ResponseEntity<Optional<Styles>> getStyleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(stylesRepositorio.findById(id));
    }
}

