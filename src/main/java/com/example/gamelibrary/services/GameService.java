package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.mappers.GameMapper;
import com.example.gamelibrary.repositories.GameRepository;
import org.springframework.stereotype.Service;
import com.example.gamelibrary.exceptions.GameNotFoundException;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> getAllGames(){

        return gameRepository.findAll()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }

    public GameDTO getGameById(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return GameMapper.toDTO(game);
    }

    public void createGame(CreateGameDTO dto){
        Game game = GameMapper.toEntity(dto);

        gameRepository.save(game);
    }

    public void updateGame(Long id, UpdateGameDTO dto) {

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        GameMapper.updateEntity(game, dto);
        gameRepository.save(game);

    }

    public void deleteGame(Long id) {

        if(!gameRepository.existsById(id)){
            throw new GameNotFoundException(id);
        }
        gameRepository.deleteById(id);
    }

    public List<GameDTO> getAllFeatured(){

        return gameRepository.findByFeaturedTrue()
                .stream()
                .map(GameMapper::toDTO)
                .toList();

    }

    public List<GameDTO> getAllFavorites(){
        return gameRepository.findByFavoriteTrue()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }

    public List<GameDTO> getRecentlyAdded(){

        return gameRepository.findTop6ByOrderByCreatedAtDesc()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }


}
