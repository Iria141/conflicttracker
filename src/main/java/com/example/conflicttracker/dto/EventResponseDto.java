package com.example.conflicttracker.dto;

import com.example.conflicttracker.entity.ConflictStatus;

import java.time.LocalDate;


/*
Es la respuesta, salida.
 */
public class EventResponseDto {

    private int id;
    private LocalDate fechaEvento;
    private String lugar;
    private String descripcion;
    private Integer conflictId; //La definimos como objeto para que pueda ser null
    private Integer factionId;



    public EventResponseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getConflictId() {
        return conflictId;
    }

    public void setConflictId(int conflictId) {
        this.conflictId = conflictId;
    }

}
