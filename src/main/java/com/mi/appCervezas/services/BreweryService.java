package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BreweryService {

    private final BreweryRepository breweryRepository;

    @Autowired
    public BreweryService(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    public Page<Brewery> getAllBreweries(Pageable pageable) {
        return breweryRepository.findAll(pageable);
    }


    public BreweryDTO getBreweryById(Long id) {
        Brewery brewery = breweryRepository.findById(id).orElse(null);
        return (brewery != null) ? new BreweryDTO(brewery) : null;
    }


}
