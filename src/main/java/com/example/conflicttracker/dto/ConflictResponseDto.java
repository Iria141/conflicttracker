package com.example.conflicttracker.dto;

import com.example.conflicttracker.entity.ConflictStatus;

import java.time.LocalDate;


/*
Es la respuesta, salida.
 */
public class ConflictResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaInicio;

    private ConflictStatus conflictStatus;


    public ConflictResponseDto() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ConflictStatus getConflictStatus() {
        return conflictStatus;
    }

    public void setConflictStatus(ConflictStatus conflictStatus) {
        this.conflictStatus = conflictStatus;
    }
}