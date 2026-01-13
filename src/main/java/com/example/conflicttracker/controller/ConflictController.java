package com.example.conflicttracker.controller;

import com.example.conflicttracker.entity.ConflictStatus;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.service.ConflictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.conflicttracker.dto.ConflictResponseDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

//Encargada directa de mostrar los datos en el json
/*Recibe peticiones
*/
@RestController
@RequestMapping("/conflicts")
public class ConflictController {

    private final ConflictService service;
    private final ConflictMapper mapper;
    private final ConflictRepository conflictRepository;

    public ConflictController(
            ConflictService conflictService,
            ConflictMapper conflictMapper,
            ConflictRepository conflictRepository) {
        this.service = conflictService;
        this.mapper = conflictMapper;
        this.conflictRepository = conflictRepository;
    }

    @PostMapping
    public ConflictResponseDto crear(
            @Valid @RequestBody ConflictRequestDto dto) {

        Conflict conflict = mapper.toEntity(dto);
        Conflict guardado = service.guardar(conflict);
        return mapper.toResponseDto(guardado);
    }

    @GetMapping
    public List<ConflictResponseDto> listar(@RequestParam(required = false) String status) {

       // System.out.println("STATUS RAW = [" + status + "]"); --> comprobacion de como llega el filtro

        if (status == null || status.trim().isEmpty()) {
            return service.obtenerTodos()
                    .stream()
                    .map(mapper::toResponseDto) //Convertir ls elementos
                    .collect(Collectors.toList());
        }
        ConflictStatus parsed;
        try {
            parsed = parseStatus(status);
        } catch (IllegalArgumentException ex) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Estado inválido: " + status
            );
        }

        return service.listarPorEstado(parsed)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConflictResponseDto> obtenerPorId(@PathVariable Long id) {
        Conflict conflict = service.obtenerPorId(id);
        ConflictResponseDto dto = mapper.toResponseDto(conflict);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ConflictResponseDto actualizar1(
            @PathVariable Long id,
            @Valid @RequestBody ConflictRequestDto dto
    ) {
        Conflict actualizado = service.actualizar(id, dto);
        return mapper.toResponseDto(actualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    private ConflictStatus parseStatus(String status) {
        String s = status.trim().toUpperCase();

        // Soporta ambos valores ACTIVE y/o ACtiVO
        return switch (s) {
            case "ACTIVE" -> ConflictStatus.ACTIVO;
            case "FROZEN" -> ConflictStatus.CONGELADO;
            case "ENDED" -> ConflictStatus.FINALIZADO;
            default -> ConflictStatus.valueOf(s);
        };
    }
}
