package com.example.conflicttracker.controller;


import com.example.conflicttracker.dto.CountryRequestDto;
import com.example.conflicttracker.dto.CountryResponseDto;
import com.example.conflicttracker.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("(/conflicts/{conflictId}/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponseDto> obtenerTodos() {
        return countryService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public CountryResponseDto obtenerPorId(@PathVariable Long id) {
        return countryService.obtenerPorId(id);
    }

    @PostMapping
    public CountryResponseDto crear(@Valid @RequestBody CountryRequestDto dto) {
        return countryService.crear(dto);
    }

    @PutMapping("/{id}")
    public CountryResponseDto actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CountryRequestDto dto
    ) {
        return countryService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        countryService.eliminar(id);
    }
}
