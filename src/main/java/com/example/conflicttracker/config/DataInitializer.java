package com.example.conflicttracker.config;

import com.example.conflicttracker.entity.*;
import com.example.conflicttracker.repository.ConflictRepository;
import com.example.conflicttracker.repository.CountryRepository;
import com.example.conflicttracker.repository.EventRepository;
import com.example.conflicttracker.repository.FactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


//Aqui añadimos unos datos iniciales para no estar incluyendo en cada reinicio informa

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatosIniciales(ConflictRepository conflictRepository,
        CountryRepository countryRepository,
        FactionRepository factionRepository,
        EventRepository eventRepository){


        return args -> {

        //Paises - que relacionamos con conflictos y bandos
            Country spain = new Country();
            spain.setNombre("España");
            spain.setCodigo("ES");

            Country france = new Country();
            france.setNombre("Francia");
            france.setCodigo("FR");

            Country ukraine = new Country();
            ukraine.setNombre("Ucrania");
            ukraine.setCodigo("UKR");

            Country russia = new Country();
            russia.setNombre("Rusia");
            russia.setCodigo("RUS");

            countryRepository.save(spain);
            countryRepository.save(france);
            countryRepository.save(ukraine);
            countryRepository.save(russia);



            //Conflictos
            Conflict conflict1 = new Conflict();
            conflict1.setNombre("Crisis de Fronteras");
            conflict1.setDescripcion("Tension en las fronteras norte y este");
            conflict1.setEstado(ConflictStatus.ACTIVO);
            conflict1.setFechaInicio(LocalDate.of(2004, 1, 16));

            Conflict conflict2 = new Conflict();
            conflict2.setNombre("Guerra de Ucrania");
            conflict2.setDescripcion("Conflicto armado entre Rusia y Ucrania");
            conflict2.setEstado(ConflictStatus.ACTIVO);
            conflict2.setFechaInicio(LocalDate.of(2022, 2, 24));

            conflictRepository.save(conflict1);
            conflictRepository.save(conflict2);

            //Bandos son los "actores" del conflicto que se relacionan con los paises
            Faction alfa = new Faction();
            alfa.setNombre("Alfa Grande");
            alfa.setConflict(conflict1);

            Faction beta = new Faction();
            beta.setNombre("Beta Movimiento");
            beta.setConflict(conflict2);

            Faction gamma = new Faction();
            gamma.setNombre("First Gamma ");
            gamma.setConflict(conflict1);


            factionRepository.save(alfa);
            factionRepository.save(beta);
            factionRepository.save(gamma);

            //Events
            Event event1 = new Event();
            event1.setFechaEvento(LocalDate.now().minusDays(10));
            event1.setDescripcion("Primera confrontación");
            event1.setConflict(conflict1);

            Event event2 = new Event();
            event2.setFechaEvento(LocalDate.now().minusDays(2));
            event2.setDescripcion("Comienzo de las negociaciones diplomáticas");
            event2.setConflict(conflict1);

            Event event3 = new Event();
            event3.setFechaEvento(LocalDate.now().minusDays(2));
            event3.setDescripcion("Tropas rusas entran en territorio Ucraniano");
            event3.setConflict(conflict2);

            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);


            //Relaciones, que completan las tabla intermedias (Pais/Conflicto y Pais/Faction)

            conflict1.getCountries().add(spain);
            conflict2.getCountries().add(france);
            conflict2.getCountries().add(ukraine);

            alfa.getCountries().add(spain);
            beta.getCountries().add(france);
            gamma.getCountries().add(ukraine);
            alfa.getCountries().add(russia);

            conflictRepository.save(conflict2);
            conflictRepository.save(conflict1);
            factionRepository.save(alfa);
            factionRepository.save(beta);
            factionRepository.save(gamma);

            System.out.println("Datos de prueba cargados correctamente");
        };
    }
}