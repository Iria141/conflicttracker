package com.example.conflicttracker.repository;

import com.example.conflicttracker.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactionRepository extends JpaRepository<Faction, Integer> {

    List<Faction> findByConflictId(int conflictId);

    boolean existsByNombreAndConflictId(String nombre, Integer conflictId); //Variable para evitar nombres repetidos


}
