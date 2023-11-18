package com.mi.appCervezas.dto;

import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.models.Style;

import java.util.Date;

public class BeerDTO {

    private Long id;
    private Long brewery_id;
    private String name;
    private Long cat_id;
    private Long style_id;
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
        this.brewery_id = beer.getBrewery().getId();
        this.name = beer.getName();
        if (beer.getCategory() != null) {
            this.cat_id = beer.getCategory().getId();
        }
        this.style_id = beer.getStyle().getId();
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

    public Long getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(Long brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public Long getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Long style_id) {
        this.style_id = style_id;
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

        if (this.brewery_id != null) {
            Brewery brewery = new Brewery();
            brewery.setId(this.brewery_id);
            beer.setBrewery(brewery);
        }

        beer.setName(this.name);

        if (this.cat_id == null) {
            throw new IllegalArgumentException("cat_id no puede ser nulo");
        } else {
            Category category = new Category();
            category.setId(this.cat_id);
            beer.setCategory(category);
        }

        if (this.style_id != null) {
            Style style = new Style();
            style.setId(this.style_id);
            beer.setStyle(style);
        }

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
