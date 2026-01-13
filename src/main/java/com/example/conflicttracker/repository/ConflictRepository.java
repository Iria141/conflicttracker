package com.example.conflicttracker.repository;

import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Accede a la BD
 */
public interface ConflictRepository extends  JpaRepository <Conflict, Long>{
    List<Conflict> findByEstado(ConflictStatus estado);

    /* Integra metodos como: findAll(), findById(id), save(conflict), deleteById(id) */

}
