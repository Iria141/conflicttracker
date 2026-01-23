package com.example.conflicttracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConflictTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConflictTrackerApplication.class, args);
	}
	@Bean
	CommandLineRunner check(@Value("${app.marker:NOT_FOUND}") String marker,
							@Value("${server.servlet.context-path:/}") String ctx) {
		return args -> System.out.println("MARKER=" + marker + " | CONTEXT_PATH=" + ctx);
	}

}


/*  Para comprobar el Index accedemos a /api/v1/index.html,
que realiza una petición fetch a GET /api/v1/conflicts y
muestra el resultado dinámicamente.*/