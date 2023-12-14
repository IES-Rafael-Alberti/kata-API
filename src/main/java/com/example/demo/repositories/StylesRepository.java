package com.example.demo.repositories;

import com.example.demo.models.Styles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylesRepository extends JpaRepository<Styles, Long> {

}