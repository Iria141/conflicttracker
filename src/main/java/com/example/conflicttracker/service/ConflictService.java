package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.dto.ConflictResponseDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import com.example.conflicttracker.exception.ConflictNotFoundException;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.repository.ConflictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
Es la logica y solo tiene que trabajar con Entity
 */

@Service
public class ConflictService {

    private final ConflictRepository repository;
    private ConflictMapper mapper;

    public ConflictService(ConflictRepository conflictRepository,  ConflictMapper mapper) {
        this.repository = conflictRepository;
        this.mapper = mapper;

    }

    public Conflict guardar(Conflict conflict) {
        return repository.save(conflict);
    }

    public List<Conflict> obtenerTodos() {
        return repository.findAll();
    }

    public Conflict obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConflictNotFoundException(id));
    }

    public Conflict actualizar(Long id, ConflictRequestDto dto) {
        Conflict conflict = repository.findById(id)
                .orElseThrow(() -> new ConflictNotFoundException(id)
                );
        mapper.actualizarEntidad(conflict, dto);
        return repository.save(conflict);
    }

    public void eliminar(Long id) {
        Conflict conflict = repository.findById(id)
                .orElseThrow(() -> new ConflictNotFoundException(id));

        repository.delete(conflict);
    }

    public List<Conflict> listarPorEstado(ConflictStatus status) {
        return repository.findByEstado(status);
    }

}

