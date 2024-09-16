package com.example.demo.dto.converter;

import com.example.demo.dto.BreweryDTO;
import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.dto.serviceDto.ServiceBreweryDTO;
import com.example.demo.entities.Breweries;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Component class for converting Brewery entities to ServiceBreweryDTO.
 */
@Component
@RequiredArgsConstructor
public class BreweryDTOConverter {
    private final ModelMapper modelMapper;
    /**
     * Converts a Brewery entity to a ServiceBreweryDTO.
     *
     * @param brewery Brewery entity to be converted.
     * @return ServiceBreweryDTO representing the converted data.
     */
    public ServiceBreweryDTO convertToDto(Breweries brewery) {
        return modelMapper.map(brewery, ServiceBreweryDTO.class);
    }
}
