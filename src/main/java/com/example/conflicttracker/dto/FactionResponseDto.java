package com.example.conflicttracker.dto;

public class FactionResponseDto {
    private Integer id;
    private String nombre;
    private Integer conflictId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
