package com.example.conflicttracker.service;

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.dto.EventRequestDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Event;
import com.example.conflicttracker.entity.Faction;
import com.example.conflicttracker.exception.ConflictNotFoundException;
import com.example.conflicttracker.mapper.ConflictMapper;
import com.example.conflicttracker.mapper.EventMapper;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.EventRepository;
import com.example.conflicttracker.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
**Es la logica y solo tiene que trabajar con Entity
** Se desarrolan  las relaciones ya marcadas en la Entidad
 */

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;
    private final EventMapper eventMapper;
    private final FactionRepository factionRepository;

    public EventService(EventRepository eventRepository,
                        ConflictRepository conflictRepository,
                        EventMapper eventMapper,
                        FactionRepository factionRepository) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
        this.eventMapper = eventMapper;
        this.factionRepository = factionRepository;
    }

    public Event crear(int conflictId, EventRequestDto dto) {
        Conflict conflict = conflictRepository.findById(conflictId)
                .orElseThrow(() ->
                        new ConflictNotFoundException(conflictId)
                );

        Faction faction = null;

        if (dto.getFactionId() != null) {
            faction = factionRepository.findById(dto.getFactionId())
                    .orElseThrow(() ->
                            new RuntimeException("Bando no encontrado con id " + dto.getFactionId())
                    );
        }

        if (dto.getFechaEvento().isBefore(conflict.getFechaInicio())) {
            throw new IllegalArgumentException(
                    "La fecha del evento no puede ser anterior al inicio del conflicto"
            );
        }


        Event event = eventMapper.toEntity(dto, conflict, faction);
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

        Faction faction = null;
        if (dto.getFactionId() != null) {
            faction = factionRepository.findById(dto.getFactionId())
                    .orElseThrow(() ->
                            new RuntimeException("Bando no encontrada con id " + dto.getFactionId())
                    );
        }

        eventMapper.actualizarEntidad(event, dto, faction);
        return eventRepository.save(event);
    }

    public void eliminar(int eventId) {
        Event event = obtenerPorId(eventId);
        eventRepository.delete(event);
    }

}

