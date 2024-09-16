package com.example.APIcerverzas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cat_name;
    private Date last_mod;
}
