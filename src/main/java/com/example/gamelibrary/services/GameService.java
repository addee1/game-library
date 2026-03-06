package com.example.gamelibrary.services;

import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
}
