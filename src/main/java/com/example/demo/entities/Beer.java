package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "beers")
@Builder
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "brewery_id")
    private int brewery_id;

    private String name;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "cat_id")
    private int cat_id;

    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "style_id")
    private int style_id;

    private Float abv;
    private Float ibu;
    private Float srm;
    private Long upc;
    private String filepath;
    private String descript;
    private Integer addUser;
    private LocalDate last_mod;
}
