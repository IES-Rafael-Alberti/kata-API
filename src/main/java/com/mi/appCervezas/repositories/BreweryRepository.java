package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Brewery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {
    Page<Brewery> findAll(Pageable pageable);


}
