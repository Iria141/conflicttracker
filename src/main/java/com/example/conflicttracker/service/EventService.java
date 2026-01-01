package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.dto.EventRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Event;
import com.example.conflicttracker.exception.ConflictNotFoundException;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.mapper.EventMapper;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Es la logica y solo tiene que trabajar con Entity
 */

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository,
                        ConflictRepository conflictRepository,
                        EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
        this.eventMapper = eventMapper;
    }

    public Event crear(int conflictId, EventRequestDto dto) {
        Conflict conflict = conflictRepository.findById(conflictId)
                .orElseThrow(() ->
                        new ConflictNotFoundException(conflictId)
                );

        Event event = eventMapper.toEntity(dto, conflict);
        return eventRepository.save(event);
    }

    public List<Event> obtenerPorConflicto(int conflictId) {
        return eventRepository.findByConflictId(conflictId);
    }

    public Event obtenerPorId(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Evento no encontrado con id " + eventId)
                );
    }

    public Event actualizar(int eventId, EventRequestDto dto) {
        Event event = obtenerPorId(eventId);
        eventMapper.actualizarEntidad(event, dto);
        return eventRepository.save(event);
    }

    public void eliminar(int eventId) {
        Event event = obtenerPorId(eventId);
        eventRepository.delete(event);
    }

}

