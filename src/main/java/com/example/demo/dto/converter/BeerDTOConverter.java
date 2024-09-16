package com.example.demo.dto.converter;


import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Component class for converting Beer entities to ServiceBeerDTOs.
 */
@Component
@RequiredArgsConstructor
public class BeerDTOConverter {

    private final ModelMapper modelMapper;


    /**
     * Converts a Beer entity to a ServiceBeerDTO.
     *
     * @param beer Beer entity to be converted.
     * @return ServiceBeerDTO representing the converted data.
     */
    public ServiceBeerDTO convertToDto(Beer beer) {
        return modelMapper.map(beer, ServiceBeerDTO.class);
    }
}
