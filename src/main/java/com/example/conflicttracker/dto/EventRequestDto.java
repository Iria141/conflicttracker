package com.example.conflicttracker.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
Aqui creamos el POST usando el DTO de entrada,  no contiene logica solo informacion
Pregunta, es la entrada
Se ñaden las validaciones siempre aqui
 */
public class EventRequestDto {

    @NotNull(message = "La fecha del evento es obligatoria")
    private LocalDate fechaEvento;

    @NotBlank(message = "El lugar es obligatorio")
    private String lugar;

    @NotBlank(message = "La descripción es obligatoria y no puede superar los 200 carácteres") @Size(max = 2000)
    private String descripcion;

    private Integer factionId;



    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFactionId() {
        return factionId;
    }

    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }
}
