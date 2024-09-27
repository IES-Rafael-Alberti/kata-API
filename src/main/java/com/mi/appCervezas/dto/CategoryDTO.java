package com.mi.appCervezas.dto;

import com.mi.appCervezas.models.Category;

import java.util.Date;
import java.util.Objects;

public class CategoryDTO extends Category {

    private Long id;
    private String cat_name;
    private Date last_mod;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.cat_name = category.getCat_name();
        this.last_mod = category.getLast_mod();
    }


    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Date getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(Date last_mod) {
        this.last_mod = last_mod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cat_name, that.cat_name) &&
                Objects.equals(last_mod, that.last_mod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cat_name, last_mod);
    }

}
