package com.mi.appCervezas.models;

import javax.persistence.*;

@Entity
@Table(name = "breweries")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String address;


    // Getters y Setters


    /*public Object getName() {
    }*/

}
