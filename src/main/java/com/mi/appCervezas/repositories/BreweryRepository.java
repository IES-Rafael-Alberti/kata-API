package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.BreweryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<BreweryModel, Long> {


}
