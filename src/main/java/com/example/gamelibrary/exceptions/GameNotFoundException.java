package com.example.gamelibrary.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Game not found with id: " + id);
    }
}
