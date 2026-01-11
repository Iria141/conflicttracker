package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictResponseDto;
import com.example.conflicttracker.dto.CountryRequestDto;
import com.example.conflicttracker.dto.CountryResponseDto;
import com.example.conflicttracker.entity.Country;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.mapper.CountryMapper;
import com.example.conflicttracker.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final ConflictMapper conflictMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper, ConflictMapper conflictMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.conflictMapper = conflictMapper;
    }

    public List<CountryResponseDto> obtenerTodos() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public CountryResponseDto obtenerPorId(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("País no encontrado"));
        return countryMapper.toResponseDto(country);
    }

    public CountryResponseDto crear(CountryRequestDto dto) {
        Country country = countryMapper.toEntity(dto);
        return countryMapper.toResponseDto(countryRepository.save(country));
    }

    public CountryResponseDto actualizar(Long id, CountryRequestDto dto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("País no encontrado"));

        countryMapper.actualizarEntidad(country, dto);
        return countryMapper.toResponseDto(countryRepository.save(country));
    }

    public void eliminar(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new EntityNotFoundException("País no encontrado");
        }
        countryRepository.deleteById(id);
    }

    //Extra

    public List<ConflictResponseDto> obtenerConflictosPorCodigoPais(String codigo) {
        Country country = countryRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("No existe el país con código: " + codigo));

        return country.getConflicts().stream()
                .map(conflictMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}