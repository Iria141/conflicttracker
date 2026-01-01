package com.example.conflicttracker.mapper;


/*
Transforma la informacion de un objetoa otro.
**NO GUARDA DATOS EN BD, NO RECIBE HTTP NI TIENE LOGICA.
 */

import com.example.conflicttracker.dto.EventRequestDto;
import com.example.conflicttracker.dto.EventResponseDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Event;
import com.example.conflicttracker.entity.Faction;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toEntity(EventRequestDto dto, Conflict conflict, Faction faction) {
        Event event = new Event();
        event.setFechaEvento(dto.getFechaEvento());
        event.setLugar(dto.getLugar());
        event.setDescripcion(dto.getDescripcion());
        event.setConflict(conflict);
        event.setFaction(faction);
        return event;
    }

    public EventResponseDto toResponseDto(Event event) {
        EventResponseDto dto = new EventResponseDto();
        dto.setId(event.getId());
        dto.setFechaEvento(event.getFechaEvento());
        dto.setLugar(event.getLugar());
        dto.setDescripcion(event.getDescripcion());
        dto.setConflictId(event.getConflict().getId());
        return dto;
    }

    public void actualizarEntidad(Event event, EventRequestDto dto, Faction faction) {
        event.setFechaEvento(dto.getFechaEvento());
        event.setLugar(dto.getLugar());
        event.setDescripcion(dto.getDescripcion());
        event.setFaction(faction);

    }
}

