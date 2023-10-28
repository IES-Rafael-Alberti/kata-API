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
    private BreweryRepository breweryRepository;

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public Brewery getBreweryById(Long id) {
        return (Brewery) breweryRepository.findById(id).orElse(null);
    }

    public Brewery addBrewery(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public void deleteBrewery(Long id) {
        breweryRepository.deleteById(id);
    }

    /*public Brewery updateBrewery(Long id, Brewery newBrewery) {
        Optional<Object> optionalBrewery = Optional.of(breweryRepository.findById(id));

        Brewery existingBrewery = (Brewery) optionalBrewery.get();
        existingBrewery.setName(newBrewery.getName());
        // ... (actualizar otros campos según sea necesario)
        return breweryRepository.save(existingBrewery);
    }*/
}

