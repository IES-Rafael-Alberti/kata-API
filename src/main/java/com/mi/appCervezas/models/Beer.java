package com.mi.appCervezas.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brewery_id", nullable = false)
    private Brewery brewery;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @Column(nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private float abv;

    @Column(nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private float ibu;

    @Column(nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private float srm;

    @Column(nullable = false, length = 40, columnDefinition = "INT DEFAULT 0")
    private int upc;

    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String filepath;

    @Column(nullable = false, length = 255)
    private String descript;

    @Column(nullable = false, length = 11, columnDefinition = "INT DEFAULT 0")
    private int add_user;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT '0000-00-00 00:00:00'")
    private Date last_mod;


    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /*public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }*/
}

