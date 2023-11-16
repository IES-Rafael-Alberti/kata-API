package com.mi.appCervezas.dto;

import com.mi.appCervezas.models.Beer;

import java.util.Date;

public class BeerDTO {

    private Long id;
    private Long breweryId;
    private String name;
    private Long categoryId;
    private Long styleId;
    private float abv;
    private float ibu;
    private float srm;
    private int upc;
    private String filepath;
    private String descript;
    private int add_user;
    private Date last_mod;

    /* Constructor sin argumentos:
    Jackson usa el constructor sin argumentos para
    crear una instancia de la clase antes de establecer los campos en ella
    */
    public BeerDTO() {
    }

    // Constructor con Beer
    // Constructor
    public BeerDTO(Beer beer) {
        this.id = beer.getId();
        this.breweryId = beer.getBreweryId();
        this.name = beer.getName();
        this.categoryId = beer.getCategoryId();
        this.styleId = beer.getStyleId();
        this.abv = beer.getAbv();
        this.ibu = beer.getIbu();
        this.srm = beer.getSrm();
        this.upc = beer.getUpc();
        this.filepath = beer.getFilepath();
        this.descript = beer.getDescript();
        this.add_user = beer.getAdd_user();
        this.last_mod = beer.getLast_mod();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
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

    // Método para transformar BeerDTO a Beer
    public Beer toBeer() {
        Beer beer = new Beer();
        beer.setId(this.id);
        beer.setBreweryId(this.breweryId);
        beer.setName(this.name);
        if (this.categoryId == null) {
            throw new IllegalArgumentException("categoryId no puede ser nulo");
        } else {
            beer.setCategoryId(this.categoryId);
        }
        beer.setStyleId(this.styleId);
        beer.setAbv(this.abv);
        beer.setIbu(this.ibu);
        beer.setSrm(this.srm);
        beer.setUpc(this.upc);
        beer.setFilepath(this.filepath);
        beer.setDescript(this.descript);
        beer.setAdd_user(this.add_user);
        beer.setLast_mod(this.last_mod);
        return beer;
    }

}
