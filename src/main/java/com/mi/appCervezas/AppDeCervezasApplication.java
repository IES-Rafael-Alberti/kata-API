package com.mi.appCervezas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.mi.appCervezas.controllers", "com.mi.appCervezas.repositories", "com.mi.appCervezas.models", "com.mi.appCervezas.services"})
public class AppDeCervezasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppDeCervezasApplication.class, args);
    }

}