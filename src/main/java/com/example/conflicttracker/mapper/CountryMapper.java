package com.example.conflicttracker.mapper;

import com.example.conflicttracker.dto.CountryRequestDto;
import com.example.conflicttracker.dto.CountryResponseDto;
import com.example.conflicttracker.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public Country toEntity(CountryRequestDto dto) {
        Country country = new Country();
        country.setNombre(dto.getName());
        country.setCodigo(dto.getCode());
        return country;
    }

    public CountryResponseDto toResponseDto(Country country) {
        CountryResponseDto dto = new CountryResponseDto();
        dto.setId(country.getId());
        dto.setNombre(country.getNombre());
        dto.setCodigo(country.getCodigo());
        return dto;
    }

    public void actualizarEntidad(Country country, CountryRequestDto dto) {
        country.setNombre(dto.getName());
        country.setCodigo(dto.getCode());
    }
}