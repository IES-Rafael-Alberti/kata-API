package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.repositories.BeerRepository;
import com.mi.appCervezas.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return beerRepository.findById(id).orElse(null);
    }

    public Beer addBeer(BeerDTO beer) {
        return beerRepository.save(beer);
    }

    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    public Beer updateBeer(Long id, BeerDTO newBeer) {
        Beer existingBeer = beerRepository.findById(id).orElse(null);
        if (existingBeer != null) {
            existingBeer.setName(newBeer.getName());
            existingBeer.setAbv(newBeer.getAbv());
            existingBeer.setIbu(newBeer.getIbu());
            existingBeer.setSrm(newBeer.getSrm());
            existingBeer.setUpc(newBeer.getUpc());
            existingBeer.setFilepath(newBeer.getFilepath());
            existingBeer.setDescript(newBeer.getDescript());
            existingBeer.setAdd_user(newBeer.getAdd_user());
            existingBeer.setLast_mod(newBeer.getLast_mod());

            // Verificar si la nueva categoría es válida
            Category newCategory = newBeer.getCategory();
            if (newCategory != null && newCategory.getId() != null) {
                // Verificar si la categoría existe en la base de datos
                Category existingCategory = categoryRepository.findById(newCategory.getId()).orElse(null);
                if (existingCategory != null) {
                    existingBeer.setCategory(existingCategory);
                } else {
                    // La categoría no existe, maneja este caso según tus necesidades
                }
            }

            return beerRepository.save(existingBeer);
        }
        return null;
    }


}

