package com.example.APIcerverzas.repos;

import com.example.APIcerverzas.modelo.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepositorio extends JpaRepository<Categories, Long> {
}
