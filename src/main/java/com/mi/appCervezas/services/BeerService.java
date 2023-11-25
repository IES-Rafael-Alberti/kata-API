package com.mi.appCervezas.services;

import com.mi.appCervezas.dto.BeerDTO;
import com.mi.appCervezas.error.BeerNotFoundException;
import com.mi.appCervezas.error.BreweryNotFoundException;
import com.mi.appCervezas.error.CategoryNotFoundException;
import com.mi.appCervezas.error.StyleNotFoundException;
import com.mi.appCervezas.models.Beer;
import com.mi.appCervezas.models.Brewery;
import com.mi.appCervezas.models.Category;
import com.mi.appCervezas.models.Style;
import com.mi.appCervezas.repositories.BeerRepository;
import com.mi.appCervezas.repositories.BreweryRepository;
import com.mi.appCervezas.repositories.CategoryRepository;
import com.mi.appCervezas.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private StyleRepository styleRepository;

    public Page<Beer> getAllBeers(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));
    }

    public boolean beerExists(Long id) {
        Optional<Beer> beer = beerRepository.findById(id);
        return beer.isPresent();
    }


    @Transactional
    public Beer addBeer(BeerDTO beerDTO) {
        Beer beer = new Beer();

        // Configurar la relación con Brewery
        Long brewery_id = beerDTO.getBrewery_id();
        if (brewery_id != null) {
            Brewery brewery = new Brewery();
            brewery.setId(brewery_id);
            beer.setBrewery(brewery);
        } else {
            beer.setBrewery(null);
        }

        // Configurar la relación con Category
        Long cat_id = beerDTO.getCat_id();
        if (cat_id != null) {
            Category category = categoryRepository.findById(cat_id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + cat_id));
            beer.setCategory(category);
        } else {
            beer.setCategory(null);
        }

        // Configurar la relación con Style
        Long style_id = beerDTO.getStyle_id();
        if (style_id != null) {
            Style style = new Style();
            style.setId(style_id);
            beer.setStyle(style);
        } else {
            beer.setStyle(null);
        }

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

        // Configurar la relación con Brewery
        if (newBeer.getBrewery() != null && newBeer.getBrewery().getId() != null) {
            Long brewery_id = newBeer.getBrewery().getId();
            Brewery existingBrewery = breweryRepository.findById(brewery_id)
                    .orElseThrow(() -> new BreweryNotFoundException("Brewery not found with id: " + brewery_id));
            existingBeer.setBrewery(existingBrewery);
        } else {
            // Manejar el caso donde brewery es nulo o brewery_id es nulo
            existingBeer.setBrewery(null);
        }

        // Configurar la relación con Style
        if (newBeer.getStyle() != null && newBeer.getStyle().getId() != null) {
            Long style_id = newBeer.getStyle().getId();
            Style existingStyle = styleRepository.findById(style_id)
                    .orElseThrow(() -> new StyleNotFoundException("Style not found with id: " + style_id));
            existingBeer.setStyle(existingStyle);
        } else {
            // Manejar el caso donde style es nulo o style_id es nulo
            existingBeer.setStyle(null);
        }

        existingBeer.setAbv(newBeer.getAbv());
        existingBeer.setIbu(newBeer.getIbu());
        existingBeer.setSrm(newBeer.getSrm());
        existingBeer.setUpc(newBeer.getUpc());
        existingBeer.setFilepath(newBeer.getFilepath());
        existingBeer.setDescript(newBeer.getDescript());
        existingBeer.setAdd_user(newBeer.getAdd_user());
        existingBeer.setLast_mod(newBeer.getLast_mod());

        // Configurar la relación con Category
        Category newCategory = newBeer.getCategory();
        if (newCategory != null && newCategory.getId() != null) {
            Long cat_id = newCategory.getId();
            Category existingCategory = categoryRepository.findById(cat_id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + cat_id));
            existingBeer.setCategory(existingCategory);
        } else {
            // Manejar el caso donde category es nulo o cat_id es nulo
            existingBeer.setCategory(null);
        }

        return beerRepository.save(existingBeer);
    }

}
