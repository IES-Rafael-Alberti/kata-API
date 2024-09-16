package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BeerDTO {

    private Long id;
    private String name;
    private Float abv;
    private Float ibu;
    private Float srm;
    private Long upc;
    private String filepath;
    private String descript;
    private Integer addUser;
    private LocalDate last_mod;


}
