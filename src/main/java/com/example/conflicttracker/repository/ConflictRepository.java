package com.example.conflicttracker.repository;

import com.example.conflicttracker.entity.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Accede a la BD
 */
public interface ConflictRepository extends  JpaRepository <Conflict, Integer>{
/* Integra metodos como: findAll(), findById(id), save(conflict), deleteById(id) */

}
