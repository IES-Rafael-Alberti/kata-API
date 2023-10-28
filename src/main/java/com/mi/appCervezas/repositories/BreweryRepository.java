package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {


}
