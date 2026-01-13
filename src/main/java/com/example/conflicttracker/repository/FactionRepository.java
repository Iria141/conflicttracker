package com.example.conflicttracker.repository;

import com.example.conflicttracker.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactionRepository extends JpaRepository<Faction, Long> {

    List<Faction> findByConflictId(Long conflictId);

    boolean existsByNombreAndConflictId(String nombre, Long conflictId); //Variable para evitar nombres repetidos

}
