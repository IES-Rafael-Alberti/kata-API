package com.example.demo.servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.converter.CategoryDTOConverter;
import com.example.demo.dto.serviceDto.ServiceCategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.error.CategoryNotFound;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.servicies.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService<Category, Long, CategoryRepository> {


    private final CategoryDTOConverter dtoConverter;

    /**
     * Retrieves a paginated list of categories.
     *
     * @param page Page number for pagination (optional, default: 0).
     * @param size Number of items to return per page (optional, default: 10).
     * @return Page of ServiceCategoryDTO containing category details.
     */

    public Page<ServiceCategoryDTO> getCategories(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> categoryPage = this.repositorio.findAll(pageRequest);

        List<ServiceCategoryDTO> categoryDTOList = categoryPage.getContent()
                .stream()
                .map(dtoConverter::convertToDto)  // Using the convertToDto method
                .collect(Collectors.toList());

        return new PageImpl<>(categoryDTOList, pageRequest, categoryPage.getTotalElements());
    }
    /**
     * Retrieves details of a specific category by its ID.
     *
     * @param id ID of the category to retrieve.
     * @return ResponseEntity with status 200 (OK) and the corresponding ServiceCategoryDTO if the category exists.
     *         ResponseEntity with status 404 (Not Found) if the category does not exist.
     */

    public ResponseEntity<ServiceCategoryDTO> getCategory(@PathVariable Long id) {
        Optional<Category> category = this.repositorio.findById(id);

        return category.map(categoryEntity -> ResponseEntity.ok(dtoConverter.convertToDto(categoryEntity)))  // Adjust this method according to your dtoConverter
                .orElseThrow(() -> new CategoryNotFound(id));
    }
    public Category newCategory(ServiceCategoryDTO newCategoryParam) {



        // En ocasiones, no necesitamos el uso de ModelMapper si la conversión que vamos a hacer
        // es muy sencilla. Con el uso de @Builder sobre la clase en cuestión, podemos realizar
        // una transformación rápida como esta.

        Category newCategory = Category.builder()
                .catName(newCategoryParam.getCatName())
                .build();


        return this.save(newCategory);

    }


    public Page<Category> findCategoryByName(String txt, Pageable pageable) {
        return this.repositorio.findCategoryByCatNameContainingIgnoreCase(txt, pageable);
    }

    public Page<Category> findCategoryByArgs(final Optional<String> categoryName, Pageable pageable) {

        Specification<Category> specBreweryName = (root, query, criteriaBuilder) -> {
            if (categoryName.isPresent()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("cat_name")),"%" + categoryName.get() + "%");
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Es decir, que no filtramos nada
            }
        };




        return this.repositorio.findAll(specBreweryName, pageable);
    }
}


