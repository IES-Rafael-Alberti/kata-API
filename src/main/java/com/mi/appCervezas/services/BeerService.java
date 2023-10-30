package com.mi.appCervezas.services;

import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).orElse(null);
    }

    public Beer addBeer(Beer beer) {
        return beerRepository.save(beer);
    }

    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    public Beer updateBeer(Long id, Beer newBeer) {
        Beer existingBeer = beerRepository.findById(id).orElse(null);
        if (existingBeer != null) {
            existingBeer.setName(newBeer.getName());
            existingBeer.setCategory(newBeer.getCategory());
            return beerRepository.save(existingBeer);
        }
        return null;
    }
}

