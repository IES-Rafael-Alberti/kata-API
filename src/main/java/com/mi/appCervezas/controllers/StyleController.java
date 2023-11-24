package com.mi.appCervezas.controllers;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping("/styles")
    public ResponseEntity<Page<StyleDTO>> getAllStyles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Style> stylePage = styleService.getAllStyles(pageable);

        List<StyleDTO> styleDTOList = stylePage.getContent().stream()
                .map(StyleDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(styleDTOList, pageable, stylePage.getTotalElements()));
    }


    @GetMapping("/style/{id}")
    public ResponseEntity<StyleDTO> getStyleById(@PathVariable Long id) {
        try {
            Style style = styleService.getStyleById(id);
            if (style != null) {
                StyleDTO styleDTO = new StyleDTO(style);
                return ResponseEntity.ok(styleDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
