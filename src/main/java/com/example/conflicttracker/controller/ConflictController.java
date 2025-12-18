package com.example.conflicttracker.controller;

import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.service.ConflictService;
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

    private final ConflictService conflictService;
    private final ConflictMapper conflictMapper;

    public ConflictController(
            ConflictService conflictService,
            ConflictMapper conflictMapper) {
        this.conflictService = conflictService;
        this.conflictMapper = conflictMapper;
    }

    @PostMapping
    public ConflictResponseDto crear(
            @Valid @RequestBody ConflictRequestDto dto) {

        Conflict conflict = conflictMapper.toEntity(dto);
        Conflict guardado = conflictService.guardar(conflict);
        return conflictMapper.toDto(guardado);
    }

    @GetMapping
    public List<ConflictResponseDto> listar() {
        return conflictService.obtenerTodos()
                .stream()
                .map(conflictMapper::toDto) //Convertir ls elementos
                .collect(Collectors.toList());
    }
}
