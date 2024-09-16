package com.example.demo.dto.converter;

import com.example.demo.dto.StyleDTO;
import com.example.demo.dto.serviceDto.ServiceStyleDTO;
import com.example.demo.entities.Style;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Component class for converting Style entities to ServiceStyleDTO.
 */
@Component
@RequiredArgsConstructor
public class StyleDTOConverter {
    private final ModelMapper modelMapper;
    /**
     * Converts a Style entity to a ServiceStyleDTO.
     *
     * @param style Style entity to be converted.
     * @return ServiceStyleDTO representing the converted data.
     */
    public ServiceStyleDTO convertToDto(Style style) {
        return modelMapper.map(style, ServiceStyleDTO.class);
    }
}
