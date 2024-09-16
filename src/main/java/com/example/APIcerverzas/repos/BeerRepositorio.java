package com.example.APIcerverzas.repos;

import com.example.APIcerverzas.modelo.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepositorio extends JpaRepository<Beer, Long> {
}
