package com.example.kataAPI.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "breweries")
@Entity
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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


}

/*
 `id` int(21) NOT NULL auto_increment,
  `name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `address1` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `address2` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `city` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `state` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `code` varchar(25) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `country` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `phone` varchar(50) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `website` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `filepath` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  `descript` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `add_user` int(11) NOT NULL default '0',
  `last_mod` datetime NOT NULL default '0000-00-00 00:00:00',
*/