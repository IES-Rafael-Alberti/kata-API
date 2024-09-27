package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    Page<Beer> findAll(Pageable pageable);

}

