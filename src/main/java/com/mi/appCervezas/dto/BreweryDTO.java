package com.mi.appCervezas.dto;

import com.mi.appCervezas.models.Brewery;

import java.util.Date;
import java.util.Objects;

public class BreweryDTO extends Brewery {

    private Long id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String code;
    private String country;
    private String phone;
    private String website;
    private String filepath;
    private String descript;
    private int add_user;
    private Date last_mod;

    public BreweryDTO(Brewery brewery) {
        this.id = brewery.getId();
        this.name = brewery.getName();
        this.address1 = brewery.getAddress1();
        this.address2 = brewery.getAddress2();
        this.city = brewery.getCity();
        this.state = brewery.getState();
        this.code = brewery.getCode();
        this.country = brewery.getCountry();
        this.phone = brewery.getPhone();
        this.website = brewery.getWebsite();
        this.filepath = brewery.getFilepath();
        this.descript = brewery.getDescript();
        this.add_user = brewery.getAdd_user();
        this.last_mod = brewery.getLast_mod();
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreweryDTO that = (BreweryDTO) o;
        return add_user == that.add_user &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address1, that.address1) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(code, that.code) &&
                Objects.equals(country, that.country) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(website, that.website) &&
                Objects.equals(filepath, that.filepath) &&
                Objects.equals(descript, that.descript) &&
                Objects.equals(last_mod, that.last_mod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address1, address2, city, state, code, country, phone, website, filepath, descript, add_user, last_mod);
    }

}
