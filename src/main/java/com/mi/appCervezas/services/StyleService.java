package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.StyleDTO;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public Page<Style> getAllStyles(Pageable pageable) {
        return styleRepository.findAll(pageable);
    }

    public Style getStyleById(Long id) {
        return styleRepository.findById(id).orElse(null);
    }
}
