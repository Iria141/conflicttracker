package com.example.conflicttracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity // hace referencia al JPA , es decir que es una tabla de BD
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Id: clave primaria
    * GeneratedValue : genera solo el codigo */
    private int id;
    private String nombre;
    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    private ConflictStatus estado;

    @Column(length = 2000)
    private String descripcion;

    public Conflict() {
        //Constructor vacio necesario para el JPA
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate startDate) {
        this.fechaInicio = fechaInicio;
    }

    public ConflictStatus getEstado() {
        return estado;
    }

    public void setEstado(ConflictStatus status) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

