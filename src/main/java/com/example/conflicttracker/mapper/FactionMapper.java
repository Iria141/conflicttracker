package com.example.conflicttracker.mapper;

import com.example.conflicttracker.dto.FactionRequestDto;
import com.example.conflicttracker.dto.FactionResponseDto;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.Faction;
import org.springframework.stereotype.Component;


@Component

public class FactionMapper {

    public Faction toEntity(FactionRequestDto dto, Conflict conflict) {
        Faction faction = new Faction();
        faction.setNombre(dto.getNombre());
        faction.setConflict(conflict);
        return faction;
    }

    public FactionResponseDto toResponseDto(Faction faction) {
        FactionResponseDto dto = new FactionResponseDto();
        dto.setId(faction.getId());
        dto.setNombre(faction.getNombre());
        dto.setConflictId(faction.getConflict().getId());
        return dto;
    }

    public void actualizarEntidad(Faction faction, FactionRequestDto dto) {
        faction.setNombre(dto.getNombre());
    }

}
