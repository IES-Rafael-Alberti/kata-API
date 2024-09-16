package com.example.demo.dto.converter;

import com.example.demo.dto.serviceDto.ServiceCategoryDTO;
import com.example.demo.entities.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDTOConverter {
    private final ModelMapper modelMapper;

    /**
     * Converts a Category entity to a ServiceCategoryDTO.
     *
     * @param category Category entity to be converted.
     * @return ServiceCategoryDTO representing the converted data.
     */
    public ServiceCategoryDTO convertToDto(Category category) {
        System.out.println(category.getCatName());
        return modelMapper.map(category, ServiceCategoryDTO.class);
    }
}
