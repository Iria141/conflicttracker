package com.example.conflicttracker.mapper;


/*
TRansforma la informacion de un objetoa otro.
**NO GUARDA DATOS EN BD, NO RECIBE HTTP NI TIENE LOGICA.
 */

import com.example.conflicttracker.dto.ConflictRequestDto;
import com.example.conflicttracker.dto.ConflictResponseDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import org.springframework.stereotype.Component;

@Component
public class ConflictMapper {

    public Conflict toEntity(ConflictRequestDto dto) {
        Conflict conflict = new Conflict();
        conflict.setNombre(dto.getNombre());
        conflict.setDescripcion(dto.getDescripcion());
        conflict.setEstado(
                dto.getEstado() != null
                        ? ConflictStatus.valueOf(dto.getEstado())
                        : null
        );        conflict.setFechaInicio(dto.getFechaInicio());
        return conflict;
    }

    public ConflictResponseDto toResponseDto(Conflict conflict) {
        if (conflict == null) {
            return null;
        }

        ConflictResponseDto dto = new ConflictResponseDto();
        dto.setId(conflict.getId());
        dto.setNombre(conflict.getNombre());
        dto.setDescripcion(conflict.getDescripcion());
        dto.setEstado(conflict.getEstado().name());
        dto.setFechaInicio(conflict.getFechaInicio());

        return dto;
    }
}

