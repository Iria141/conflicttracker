package com.example.conflicttracker.dto;

import com.example.conflicttracker.entity.ConflictStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class FormularioConflict {
    @NotBlank
    private String nombre;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private ConflictStatus estado;

    @NotBlank
    private String descripcion;

    // getters/setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public ConflictStatus getEstado() { return estado; }
    public void setEstado(ConflictStatus estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
