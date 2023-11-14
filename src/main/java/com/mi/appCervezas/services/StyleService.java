package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<StyleDTO> getAllStyles() {
        List<Style> styles = styleRepository.findAll();
        List<StyleDTO> styleDTOs = new ArrayList<>();

        for (Style style : styles) {
            styleDTOs.add(new StyleDTO(style));

        }

        return styleDTOs;
    }

    public Style getStyleById(Long id) {
        return styleRepository.findById(id).orElse(null);
    }
}
