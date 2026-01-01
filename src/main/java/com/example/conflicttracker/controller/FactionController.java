package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.FactionRequestDto;
import com.example.conflicttracker.dto.FactionResponseDto;
import com.example.conflicttracker.mapper.FactionMapper;
import com.example.conflicttracker.service.FactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conflicts/{conflictId}/factions")
public class FactionController {
    private final FactionService factionService;
    private final FactionMapper factionMapper;

    public FactionController(
            FactionService factionService,
            FactionMapper factionMapper) {
        this.factionService = factionService;
        this.factionMapper = factionMapper;
    }

    @PostMapping
    public FactionResponseDto crear(
            @PathVariable int conflictId,
            @Valid @RequestBody FactionRequestDto dto) {
        return factionMapper.toResponseDto(
                factionService.crear(conflictId, dto)
        );
    }

    @GetMapping
    public List<FactionResponseDto> listar(
            @PathVariable int conflictId) {
        return factionService.listarPorConflicto(conflictId)
                .stream()
                .map(factionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{factionId}")
    public FactionResponseDto obtener(
            @PathVariable int factionId) {
        return factionMapper.toResponseDto(
                factionService.obtenerPorId(factionId)
        );
    }

    @PutMapping("/{factionId}")
    public FactionResponseDto actualizar(
            @PathVariable int factionId,
            @Valid @RequestBody FactionRequestDto dto) {
        return factionMapper.toResponseDto(
                factionService.actualizar(factionId, dto)
        );
    }

    @DeleteMapping("/{factionId}")
    public void eliminar(@PathVariable int factionId) {
        factionService.eliminar(factionId);
    }
}
