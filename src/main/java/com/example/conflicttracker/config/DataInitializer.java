package com.example.conflicttracker.config;

import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import com.example.conflicttracker.repository.ConflictRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatosIniciales(ConflictRepository repositorio) {
        return args -> {

            Conflict c1 = new Conflict();
            c1.setNombre("Conflicto laboral");
            c1.setDescripcion("Conflicto entre empleados y dirección");
            c1.setEstado(ConflictStatus.ACTIVO);
            c1.setFechaInicio(LocalDate.of(2024,1,1));

            Conflict c2 = new Conflict();
            c2.setNombre("Conflicto vecinal");
            c2.setDescripcion("Problemas de convivencia");
            c2.setEstado(ConflictStatus.CONGELADO);
            c2.setFechaInicio(LocalDate.of(2023, 6, 15));

            repositorio.save(c1);
            repositorio.save(c2);
        };
    }
}