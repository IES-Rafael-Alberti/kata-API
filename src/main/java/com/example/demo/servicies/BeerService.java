package com.example.demo.servicies;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.converter.BeerDTOConverter;
import com.example.demo.dto.serviceDto.ServiceBeerDTO;
import com.example.demo.entities.Beer;
import com.example.demo.repos.BeerRepository;
import com.example.demo.servicies.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class BeerService extends BaseService<Beer, Long, BeerRepository> {

    private final BeerDTOConverter dtoConverter;

    /**
     * Retrieves a paginated list of beers.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceBeerDTO containing beer details.
     */
    public Page<ServiceBeerDTO> getBeers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Beer> beerPage = this.repositorio.findAll(pageRequest);
        List<ServiceBeerDTO> beerDTOList = beerPage.getContent()
                .stream()
                .map(dtoConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(beerDTOList, pageRequest, beerPage.getTotalElements());
    }
    /**
     * Creates a new beer.
     *
     * @param beer ServiceBeerDTO representing the beer to be created.
     * @return ResponseEntity with status 201 (Created) and the created ServiceBeerDTO.
     */
    public ResponseEntity<ServiceBeerDTO> postBeer(@RequestBody ServiceBeerDTO beer){
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoConverter.convertToDto(newBeer(beer)));
    }

    public ResponseEntity<ServiceBeerDTO> getBeer(@PathVariable Long id) {
        Optional<Beer> beer = this.repositorio.findById(id);

        return beer.map(beerEntity -> ResponseEntity.ok(dtoConverter.convertToDto(beerEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    /**
     * Retrieves details of a specific beer by its ID.
     *
     * @param id ID of the beer to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceBeerDTO if the beer exists.
     *         ResponseEntity with status 404 (Not Found) if the beer does not exist.
     */
    public ResponseEntity<Beer> removeBeer(@PathVariable Long id){
        Beer beer = this.repositorio.findById(id).orElseThrow(() -> new error.BeerNotFound(id));
        this.repositorio.delete(beer);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the details of a specific beer by its ID.
     *
     * @param id ID of the beer to update.
     * @param updatedBeer ServiceBeerDTO representing the updated beer details.
     * @return ServiceBeerDTO with the updated details.
     * @throws error.BeerNotFound Exception thrown if the beer with the given ID is not found (status 404).
     */
    public ServiceBeerDTO updateBeer(@PathVariable Long id, @RequestBody ServiceBeerDTO updatedBeer) {

        return this.repositorio.findById(id).map(beerToUpdate ->{
            beerToUpdate.setName(updatedBeer.getName());
            beerToUpdate.setAbv(updatedBeer.getAbv());
            beerToUpdate.setIbu(updatedBeer.getIbu());
            beerToUpdate.setSrm(updatedBeer.getSrm());
            beerToUpdate.setDescript(updatedBeer.getDescript());
            return dtoConverter.convertToDto(this.repositorio.save(beerToUpdate));
        }).orElseThrow(() -> new error.BeerNotFound(id));
    }

    public Beer newBeer(ServiceBeerDTO newBeerParam) {

        Beer newBeer = Beer.builder()
                .name(newBeerParam.getName())
                .abv(newBeerParam.getAbv())
                .ibu(newBeerParam.getIbu())
                .srm(newBeerParam.getSrm())
                .descript(newBeerParam.getDescript())
                .upc(newBeerParam.getUpc())
                .addUser(newBeerParam.getAddUser())
                .filepath(newBeerParam.getFilepath())
                .last_mod(LocalDate.now())
                .build();

        return this.save(newBeer);

    }


    public Page<Beer> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findBeerByNameContainsIgnoreCase(txt, pageable);
    }

    public Page<Beer> findCategoryByArgs(final Optional<String> beerName, Pageable pageable) {

        Specification<Beer> specBeerName = (root, query, criteriaBuilder) -> {
            if (beerName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + beerName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };




        return this.repositorio.findAll(specBeerName, pageable);
    }
}
