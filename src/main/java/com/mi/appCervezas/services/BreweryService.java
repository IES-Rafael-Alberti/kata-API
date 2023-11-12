package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.BreweryDTO;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreweryService {

    private final BreweryRepository breweryRepository;

    @Autowired
    public BreweryService(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    public List<BreweryDTO> getAllBreweries() {
        List<Brewery> breweries = breweryRepository.findAll();
        List<BreweryDTO> breweryDTOs = new ArrayList<>();

        for (Brewery brewery : breweries) {
            breweryDTOs.add(new BreweryDTO(brewery));
        }

        return breweryDTOs;
    }

    public BreweryDTO getBreweryById(Long id) {
        Brewery brewery = breweryRepository.findById(id).orElse(null);
        return (brewery != null) ? new BreweryDTO(brewery) : null;
    }


}
