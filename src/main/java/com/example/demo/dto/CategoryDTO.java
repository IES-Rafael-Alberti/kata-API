package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String catName;
    private LocalDate lastMod;

}
