package com.mi.appCervezas.services;

import com.mi.appCervezas.error.BeerNotFoundException;
import com.mi.appCervezas.error.CategoryNotFoundException;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.repositories.BeerRepository;
import com.mi.appCervezas.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));
    }

    @Transactional
    public Beer addBeer(Beer beerDTO) {
        Beer beer = new Beer();

        beer.setBreweryId(beerDTO.getBreweryId());
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
        beer.setIbu(beerDTO.getIbu());
        beer.setSrm(beerDTO.getSrm());
        beer.setUpc(beerDTO.getUpc());
        beer.setFilepath(beerDTO.getFilepath());
        beer.setDescript(beerDTO.getDescript());
        beer.setAdd_user(beerDTO.getAdd_user());
        beer.setLast_mod(beerDTO.getLast_mod());

        return beerRepository.save(beer);
    }


    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    @Transactional
    public Beer updateBeer(Long id, Beer newBeer) {
        Beer existingBeer = beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));

        if (newBeer.getName() != null) {
            existingBeer.setName(newBeer.getName());
        }

        if (newBeer.getBreweryId() != null) {
            existingBeer.setBreweryId(newBeer.getBreweryId());
        }

        if (newBeer.getStyleId() != null) {
            existingBeer.setStyleId(newBeer.getStyleId());
        }

        existingBeer.setAbv(newBeer.getAbv());
        existingBeer.setIbu(newBeer.getIbu());
        existingBeer.setSrm(newBeer.getSrm());
        existingBeer.setUpc(newBeer.getUpc());
        existingBeer.setFilepath(newBeer.getFilepath());
        existingBeer.setDescript(newBeer.getDescript());
        existingBeer.setAdd_user(newBeer.getAdd_user());
        existingBeer.setLast_mod(newBeer.getLast_mod());

        // Verificar si la nueva categoría es válida
        Long categoryId = newBeer.getCategoryId();
        if (categoryId != null) {
            // Verificar si la categoría existe en la base de datos
            Category existingCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
            existingBeer.setCategoryId(categoryId);
        }

        return beerRepository.save(existingBeer);
    }


}
