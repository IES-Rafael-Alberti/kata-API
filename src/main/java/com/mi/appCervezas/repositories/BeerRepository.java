package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {


}

