package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StyleDTO {
    private Long id;
    private String styleName;
    private LocalDate lastModified;
    private Long categoryId; // Assuming you only need the category ID in the DTO

}
