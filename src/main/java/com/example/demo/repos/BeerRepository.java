package com.example.demo.repos;

import com.example.demo.entities.Beer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
    Page<Beer> findBeerByNameContainsIgnoreCase(String txt, Pageable pageable);

}