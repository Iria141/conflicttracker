package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.dto.ConflictResponseDto;
import com.example.conflicttracker.dto.EventRequestDto;
import com.example.conflicttracker.dto.EventResponseDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Event;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.mapper.EventMapper;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.service.ConflictService;
import com.example.conflicttracker.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/* Encargada directa de mostrar los datos en el json
** Recibe peticiones
*/
@RestController
@RequestMapping("/conflicts/{conflictId}/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public EventResponseDto crear(
            @PathVariable int conflictId,
            @Valid @RequestBody EventRequestDto dto
    ) {
        Event event = eventService.crear(conflictId, dto);
        return eventMapper.toResponseDto(event);
    }

    @GetMapping
    public List<EventResponseDto> listarPorConflicto(
            @PathVariable int conflictId
    ) {
        return eventService.obtenerPorConflicto(conflictId)
                .stream()
                .map(eventMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{eventId}")
    public EventResponseDto obtenerPorId(
            @PathVariable int conflictId,
            @PathVariable int eventId
    ) {
        Event event = eventService.obtenerPorId(eventId);
        return eventMapper.toResponseDto(event);
    }

    @PutMapping("/{eventId}")
    public EventResponseDto actualizar(
            @PathVariable int conflictId,
            @PathVariable int eventId,
            @Valid @RequestBody EventRequestDto dto
    ) {
        Event event = eventService.actualizar(eventId, dto);
        return eventMapper.toResponseDto(event);
    }

    @DeleteMapping("/{eventId}")
    public void eliminar(
            @PathVariable int conflictId,
            @PathVariable int eventId
    ) {
        eventService.eliminar(eventId);
    }
}