package com.example.conflicttracker.repository;


import com.example.conflicttracker.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}