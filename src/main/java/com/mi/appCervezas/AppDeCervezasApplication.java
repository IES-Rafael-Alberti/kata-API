package com.mi.appCervezas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.mi.appCervezas.models")
@EnableJpaRepositories(basePackages = "com.mi.appCervezas.repositories")
@ComponentScan(basePackages = {"com.mi.appCervezas.controllers", "com.mi.appCervezas.services"})
public class AppDeCervezasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppDeCervezasApplication.class, args);
    }

}