package com.example.conflicttracker.controller;

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
            ConflictMapper conflictMapper, ConflictRepository conflictRepository) {
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
    public List<ConflictResponseDto> listar() {
        return service.obtenerTodos()
                .stream()
                .map(mapper::toResponseDto) //Convertir ls elementos
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictResponseDto> obtenerPorId(@PathVariable int id) {
        Conflict conflict = service.obtenerPorId(id);
        ConflictResponseDto dto = mapper.toResponseDto(conflict);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ConflictResponseDto actualizar1(
            @PathVariable int id,
            @Valid @RequestBody ConflictRequestDto dto
    ) {
        Conflict actualizado = service.actualizar(id, dto);
        return mapper.toResponseDto(actualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }


}