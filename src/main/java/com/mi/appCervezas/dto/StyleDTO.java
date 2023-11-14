package com.mi.appCervezas.dto;

import com.mi.appCervezas.models.Style;

import java.util.Date;

public class StyleDTO extends Style {

    private Long id;
    private Integer cat_id;
    private String style_name;
    private Date last_mod;

    public StyleDTO(Style style) {
        this.id = style.getId();
        this.cat_id = style.getCat_id();
        this.style_name = style.getStyle_name();
        this.last_mod = style.getLast_mod();
    }

    // Constructores, si es necesario

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public Date getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(Date last_mod) {
        this.last_mod = last_mod;
    }
}
