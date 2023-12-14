package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBeerDTO {

    private int brewery_id = 0;
    private String name;
    private int cat_id = 0;
    private int style_id = 0;
    private float abv = 0;
    private float ibu = 0;
    private float srm = 0;
    private float upc = 0;
    private String filepath;
    private String descript;
    private int add_user = 0;

}
