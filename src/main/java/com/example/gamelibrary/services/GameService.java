package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.mappers.GameMapper;
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

    public Game createGame(CreateGameDTO dto){
        Game game = GameMapper.toEntity(dto);

        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {

        Game game = getGameById(id);

        gameRepository.delete(game);
    }

    public Game getGameById(Long id){
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game updateGame(Long id, UpdateGameDTO dto) {

        Game game = getGameById(id);

        GameMapper.updateEntity(game, dto);

        return gameRepository.save(game);
    }
}
