package com.example.demo.repositories;

import com.example.demo.models.Breweries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweriesRepository extends JpaRepository<Breweries, Long> {

}
