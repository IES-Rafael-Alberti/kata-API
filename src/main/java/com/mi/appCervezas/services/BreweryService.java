package com.mi.appCervezas.services;

import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    @Autowired
    BreweryRepository breweryRepository;

    public BreweryService(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public Brewery getBreweryById(Long id) {
        return breweryRepository.findById(id).orElse(null);
    }

    public Brewery addBrewery(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public void deleteBrewery(Long id) {
        breweryRepository.deleteById(id);
    }

    public Brewery updateBrewery(Long id, Brewery newBrewery) {
        Optional<Brewery> optionalBrewery = breweryRepository.findById(id);

        if (optionalBrewery.isPresent()) {
            Brewery existingBrewery = optionalBrewery.get();
            existingBrewery.setName(newBrewery.getName());
            return breweryRepository.save(existingBrewery);
        } else {
            // Manejo si no se encuentra la cervecería
            return null;
        }
    }
}
