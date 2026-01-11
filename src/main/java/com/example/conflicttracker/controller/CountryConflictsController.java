package com.example.conflicttracker.controller;

//Generado para consultas exclusivas del pais

import com.example.conflicttracker.dto.ConflictResponseDto;
import com.example.conflicttracker.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryConflictsController {

    private final CountryService countryService;

    public CountryConflictsController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{code}/conflicts")
    public List<ConflictResponseDto> conflictosPorCodigo(@PathVariable String code) {
        return countryService.obtenerConflictosPorCodigoPais(code);
    }
}

