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

    /**
     * Recupera una página de cervezas usando paginación y ordenación.
     *
     * @param pageable Objeto Pageable que especifica la paginación y ordenación de los resultados.
     * @return Página de objetos Beer.
     */
    public Page<Beer> getAllBeers(Pageable pageable) {
        // Utiliza el método findAll del repositorio de cervezas para obtener una página de cervezas
        return beerRepository.findAll(pageable);
    }

    /**
     * Recupera una cerveza por su ID.
     *
     * @param id El ID de la cerveza que se va a recuperar.
     * @return Objeto Beer correspondiente al ID proporcionado.
     * @throws BeerNotFoundException Si no se encuentra ninguna cerveza con el ID proporcionado.
     */
    public Beer getBeerById(Long id) {
        // Usa el método findById del repositorio de cervezas para obtener una cerveza por su ID
        // Si no se encuentra, lanza una excepción BeerNotFoundException
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));
    }

    /**
     * Comprueba la existencia de una cerveza por su ID.
     *
     * @param id El ID de la cerveza que se va a comprobar.
     * @return true si la cerveza existe, false si no.
     */
    public boolean beerExists(Long id) {
        // Intenta obtener la cerveza por su ID desde el repositorio
        Optional<Beer> beer = beerRepository.findById(id);

        // Devuelve true si la cerveza está presente en el repositorio, false si no
        return beer.isPresent();
    }


    /**
     * Agrega una nueva cerveza a la base de datos.
     *
     * @param beerDTO Objeto BeerDTO que contiene la información de la nueva cerveza.
     * @return Objeto Beer recién agregado a la base de datos.
     */
    @Transactional
    public Beer addBeer(BeerDTO beerDTO) {
        // Crear una nueva instancia de Beer
        Beer beer = new Beer();

        // Configurar la relación con Brewery
        Long brewery_id = beerDTO.getBrewery_id();
        if (brewery_id != null) {
            // Si se proporciona un ID de cervecería, configurar la relación
            Brewery brewery = new Brewery();
            brewery.setId(brewery_id);
            beer.setBrewery(brewery);
        } else {
            // Si no se proporciona un ID de cervecería, establecer la relación a null
            beer.setBrewery(null);
        }

        // Configurar la relación con Category
        Long cat_id = beerDTO.getCat_id();
        if (cat_id != null) {
            // Si se proporciona un ID de categoría, obtener la categoría desde el repositorio
            Category category = categoryRepository.findById(cat_id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + cat_id));
            beer.setCategory(category);
        } else {
            beer.setCategory(null);
        }

        // Configurar la relación con Style
        Long style_id = beerDTO.getStyle_id();
        if (style_id != null) {
            // Si se proporciona un ID de estilo, configurar la relación
            Style style = new Style();
            style.setId(style_id);
            beer.setStyle(style);
        } else {
            beer.setStyle(null);
        }

        // Configuración de demás atributos de la cerveza
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
        beer.setIbu(beerDTO.getIbu());
        beer.setSrm(beerDTO.getSrm());
        beer.setUpc(beerDTO.getUpc());
        beer.setFilepath(beerDTO.getFilepath());
        beer.setDescript(beerDTO.getDescript());
        beer.setAdd_user(beerDTO.getAdd_user());
        beer.setLast_mod(beerDTO.getLast_mod());

        // Almacenar la nueva cerveza en la base de datos y devolverla
        return beerRepository.save(beer);
    }


    /**
     * Elimina una cerveza de la base de datos por su ID.
     *
     * @param id El ID de la cerveza que se va a eliminar.
     */
    public void deleteBeer(Long id) {
        // Utiliza el método deleteById del repositorio de cervezas para eliminar la cerveza por su ID
        beerRepository.deleteById(id);
    }

    /**
     * Actualiza una cerveza existente en la base de datos por su ID.
     *
     * @param id      El ID de la cerveza que se va a actualizar.
     * @param newBeer Objeto Beer que contiene la información actualizada para la cerveza.
     * @return Objeto Beer actualizado en la base de datos.
     */
    @Transactional
    public Beer updateBeer(Long id, Beer newBeer) {
        Beer existingBeer = beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException("Beer not found with id: " + id));

        // Actualizar el nombre de la cerveza si se proporciona un nuevo nombre
        if (newBeer.getName() != null) {
            existingBeer.setName(newBeer.getName());
        }

        // Configurar la relación con Brewery
        if (newBeer.getBrewery() != null && newBeer.getBrewery().getId() != null) {
            // Si se proporciona un nuevo objeto Brewery con un ID válido, actualizar la relación
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
            // Si se proporciona un nuevo objeto Style con un ID válido, actualizar la relación
            Long style_id = newBeer.getStyle().getId();
            Style existingStyle = styleRepository.findById(style_id)
                    .orElseThrow(() -> new StyleNotFoundException("Style not found with id: " + style_id));
            existingBeer.setStyle(existingStyle);
        } else {
            // Manejar el caso donde style es nulo o style_id es nulo
            existingBeer.setStyle(null);
        }

        // Configuración de otros atributos de la cerveza
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
            // Si se proporciona un nuevo objeto Category con un ID válido, actualizar la relación
            Long cat_id = newCategory.getId();
            Category existingCategory = categoryRepository.findById(cat_id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + cat_id));
            existingBeer.setCategory(existingCategory);
        } else {
            // Manejar el caso donde category es nulo o cat_id es nulo
            existingBeer.setCategory(null);
        }

        // Almacenar la cerveza actualizada en la base de datos y devolverla
        return beerRepository.save(existingBeer);
    }

}
