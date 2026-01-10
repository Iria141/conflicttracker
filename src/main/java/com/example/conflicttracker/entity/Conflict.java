package com.example.conflicttracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity // hace referencia al JPA , es decir que es una tabla de BD
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Id: clave primaria
    * GeneratedValue : genera solo el codigo */
    private Integer id;
    private String nombre;
    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    private ConflictStatus estado;

    @Column(length = 2000)
    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "conflict_country",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )

    private Set<Country> countries = new HashSet<>();

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

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ConflictStatus getEstado() {
        return estado;
    }

    public void setEstado(ConflictStatus estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}

