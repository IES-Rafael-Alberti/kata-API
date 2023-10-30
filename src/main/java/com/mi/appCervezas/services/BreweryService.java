package com.mi.appCervezas.services;

import com.mi.appCervezas.models.BreweryModel;
import com.mi.appCervezas.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    @Autowired
    private BreweryRepository breweryRepository;

    public List<BreweryModel> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public BreweryModel getBreweryById(Long id) {
        return breweryRepository.findById(id).orElse(null);
    }

    public BreweryModel addBrewery(BreweryModel brewery) {
        return breweryRepository.save(brewery);
    }

    public void deleteBrewery(Long id) {
        breweryRepository.deleteById(id);
    }

    public BreweryModel updateBrewery(Long id, BreweryModel newBrewery) {
        Optional<BreweryModel> optionalBrewery = breweryRepository.findById(id);

        if (optionalBrewery.isPresent()) {
            BreweryModel existingBrewery = optionalBrewery.get();
            existingBrewery.setName(newBrewery.getName());
            return breweryRepository.save(existingBrewery);
        } else {
            // Manejo si no se encuentra la cervecería
            return null;
        }
    }

}


