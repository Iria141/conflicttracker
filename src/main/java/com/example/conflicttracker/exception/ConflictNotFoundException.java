package com.example.conflicttracker.exception;

public class ConflictNotFoundException extends RuntimeException {

    public ConflictNotFoundException(Long id) {
        super("No existe ningún conflicto con id " + id);
    }
}