package com.example.kataAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kataAPI.model.Beer;
public interface repo_beer extends JpaRepository<Beer, Integer> {

    Beer findByName(String name);
}


