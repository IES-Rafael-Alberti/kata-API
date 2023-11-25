package com.mi.appCervezas.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brewery_id", nullable = false)
    private Brewery brewery;

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

    @Column(nullable = false)
    private Date last_mod;

    public Beer() {
    }


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public float getIbu() {
        return ibu;
    }

    public void setIbu(float ibu) {
        this.ibu = ibu;
    }

    public float getSrm() {
        return srm;
    }

    public void setSrm(float srm) {
        this.srm = srm;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getAdd_user() {
        return add_user;
    }

    public void setAdd_user(int add_user) {
        this.add_user = add_user;
    }

    public Date getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(Date last_mod) {
        this.last_mod = last_mod;
    }

}
