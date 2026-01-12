package com.example.conflicttracker.dto;

public class FactionResponseDto {
    private Long id;
    private String nombre;
    private Integer conflictId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getConflictId() {
        return conflictId;
    }

    public void setConflictId(Integer conflictId) {
        this.conflictId = conflictId;
    }
}
