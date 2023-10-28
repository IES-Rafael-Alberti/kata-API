package com.mi.appCervezas.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_name", nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String last_mod;



    // Getters y Setters

}
