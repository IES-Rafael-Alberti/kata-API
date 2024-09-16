package com.example.demo.servicies;


import com.example.demo.dto.converter.BreweryDTOConverter;
import com.example.demo.dto.serviceDto.ServiceBreweryDTO;
import com.example.demo.entities.Breweries;
import com.example.demo.error.BreweryNotFound;
import com.example.demo.repos.BreweryRepository;
import com.example.demo.servicies.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.criteria.Predicate;




@Service
@RequiredArgsConstructor
public class BreweriesService extends BaseService<Breweries, Long, BreweryRepository> {



    private final BreweryDTOConverter dtoConverter;

    /**
     * Retrieves a paginated list of breweries.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceBreweryDTO containing brewery details.
     */
    public Page<ServiceBreweryDTO> getBreweryList(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Breweries> beerPage = this.repositorio.findAll(pageRequest);
        List<ServiceBreweryDTO> beerDTOList = beerPage.getContent()
                .stream()
                .map(dtoConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(beerDTOList, pageRequest, beerPage.getTotalElements());
    }

    /**
     * Retrieves details of a specific brewery by its ID.
     *
     * @param id ID of the brewery to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceBreweryDTO if the brewery exists.
     *         ResponseEntity with status 404 (Not Found) if the brewery does not exist.
     */
    public ResponseEntity<ServiceBreweryDTO> getBrewery(@PathVariable Long id) {
        Optional<Breweries> brewery = this.repositorio.findById(id);

        return brewery.map(breweryEntity -> ResponseEntity.ok(dtoConverter.convertToDto(breweryEntity)))
                .orElseThrow(() -> new BreweryNotFound(id));
    }

}
