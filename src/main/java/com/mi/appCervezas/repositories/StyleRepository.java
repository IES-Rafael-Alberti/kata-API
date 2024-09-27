package com.mi.appCervezas.repositories;

import com.mi.appCervezas.models.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Page<Style> findAll(Pageable pageable);

}
