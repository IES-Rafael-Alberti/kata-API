package com.example.kataAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "beers")
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int cat_id;
    private int style_id;
    private float abv;
    private float ibu;
    private float srm;
    private float upc;
    private String filepath;
    private String descript;
    private int add_user;
    private Date last_mod;


}

/*
id` int(21) NOT NULL auto_increment,
  `brewery_id` int(21) NOT NULL default '0',
  `name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `cat_id` int(11) NOT NULL default '0',
  `style_id` int(11) NOT NULL default '0',
  `abv` float NOT NULL default '0',
  `ibu` float NOT NULL default '0',
  `srm` float NOT NULL default '0',
  `upc` int(40) NOT NULL default '0',
  `filepath` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `descript` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `add_user` int(11) NOT NULL default '0',
  `last_mod` datetime NOT NULL default '0000-00-00 00:00:00',
 */