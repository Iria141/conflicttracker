package com.example.conflicttracker.dto;

import java.time.LocalDate;


/*
Es la respuesta, salida.
 */
public class EventResponseDto {

    private Long id;
    private LocalDate fechaEvento;
    private String lugar;
    private String descripcion;
    private Long conflictId; //La definimos como objeto para que pueda ser null




    public EventResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }

}
