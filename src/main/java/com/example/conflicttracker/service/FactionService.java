package com.example.conflicttracker.service;


import com.example.conflicttracker.dto.FactionRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Faction;
import com.example.conflicttracker.exception.ConflictNotFoundException;
import com.example.conflicttracker.mapper.FactionMapper;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final FactionMapper factionMapper;

    public FactionService(
            FactionRepository factionRepository,
            ConflictRepository conflictRepository,
            FactionMapper factionMapper) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.factionMapper = factionMapper;
    }

    public Faction crear(Long conflictId, FactionRequestDto dto) {
        Conflict conflict = conflictRepository.findById(conflictId)
                .orElseThrow(() -> new ConflictNotFoundException(conflictId));

        if (factionRepository.existsByNombreAndConflictId(dto.getNombre(), conflictId)) {
            throw new IllegalArgumentException(
                    "Ya existe un bando con ese nombre en este conflicto"
            );
        }

        Faction faction = factionMapper.toEntity(dto, conflict);
        return factionRepository.save(faction);
    }

    public List<Faction> listarPorConflicto(Long conflictId) {
        return factionRepository.findByConflictId(conflictId);
    }

    public Faction obtenerPorId(Long id) {
        return factionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Facción no encontrada con id " + id)
                );
    }

    public Faction actualizar(Long id, FactionRequestDto dto) {
        Faction faction = obtenerPorId(id);

        factionMapper.actualizarEntidad(faction, dto);
        return factionRepository.save(faction);
    }

    public void eliminar(Long id) {
        Faction faction = obtenerPorId(id);
        factionRepository.delete(faction);
    }

}
