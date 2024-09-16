package com.example.demo.repos;

import com.example.demo.entities.Breweries;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface  BreweryRepository extends JpaRepository<Breweries, Long>, JpaSpecificationExecutor<Breweries> {
    Page<Breweries> findBreweriesByNameContainsIgnoreCase(String txt, Pageable pageable);

}
