package com.example.conflicttracker.dto;

import jakarta.validation.constraints.NotBlank;

public class FactionRequestDto {

    @NotBlank(message = "El nombre de la facción es obligatorio")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
