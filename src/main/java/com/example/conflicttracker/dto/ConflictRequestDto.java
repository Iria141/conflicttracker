package com.example.conflicttracker.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
Aqui creamos el POST usando el DTO de entrada,  no contiene logica solo informacion
Pregunta, es la entrada
Se ñaden las validaciones siempre aqui
 */
public class ConflictRequestDto {

    @NotBlank(message = "El nombre es obligatorio") //Se usa en Strings
    @Size(max = 255, message = "El nombre no puede superar 255 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 2000, message = "La descripción no puede superar 2000 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio") //En fechas y enums
    private String estado;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;


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
}
