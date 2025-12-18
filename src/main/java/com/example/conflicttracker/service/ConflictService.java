package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import com.example.conflicttracker.repository.ConflictRepository;
import org.springframework.stereotype.Service;
import com.example.conflicttracker.dto.ConflictResponseDto;

import java.util.List;
import java.util.stream.Collectors;

/*
Es la logica y solo tiene que trabajar con Entity
 */

@Service
public class ConflictService {

    private final ConflictRepository conflictRepository;

    public ConflictService(ConflictRepository conflictRepository) {
        this.conflictRepository = conflictRepository;
    }

    public Conflict guardar(Conflict conflict) {
        return conflictRepository.save(conflict);
    }

    public List<Conflict> obtenerTodos() {
        return conflictRepository.findAll();
    }
}

