package com.mi.appCervezas.models;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_id", nullable = false)
    private Long categoryId;

    @Column(name = "style_name", nullable = false)
    private String styleName;

    @Column(nullable = false)
    private String last_mod;



    // Getters y Setters

}
