package com.example.demo.servicies;

import com.example.demo.dto.converter.StyleDTOConverter;
import com.example.demo.dto.serviceDto.ServiceStyleDTO;
import com.example.demo.entities.Style;
import com.example.demo.error.StyleNotFound;
import com.example.demo.repos.StyleRepository;
import com.example.demo.servicies.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleService  extends BaseService<Style, Long, StyleRepository> {


    private final StyleDTOConverter dtoConverter;
    /**
     * Retrieves a paginated list of styles.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceStyleDTO containing style details.
     */
    public Page<ServiceStyleDTO> getStyles(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Style> stylePage = this.repositorio.findAll(pageRequest);
        List<ServiceStyleDTO> styleDTOList = stylePage.getContent()
                .stream()
                .map(dtoConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(styleDTOList, pageRequest, stylePage.getTotalElements());
    }
    public ResponseEntity<ServiceStyleDTO> getStyle(@PathVariable Long id) {
        Optional<Style> style = this.repositorio.findById(id);

        return style.map(styleEntity -> ResponseEntity.ok(dtoConverter.convertToDto(styleEntity)))
                .orElseThrow(() -> new StyleNotFound(id));
    }
    public Style newCategory(ServiceStyleDTO newStyleParam) {
        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Style newStyle = Style.builder()
                .styleName(newStyleParam.getStyleName())
                .build();
        return this.save(newStyle);
    }

    public Page<Style> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findStyleByStyleNameContainingIgnoreCase(txt, pageable);
    }

    public Page<Style> findCategoryByArgs(final Optional<String> categoryName, Pageable pageable) {

        Specification<Style> specBreweryName = (root, query, criteriaBuilder) -> {
            if (categoryName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("style_name")),"%" + categoryName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };
        return this.repositorio.findAll(specBreweryName, pageable);
    }
}


