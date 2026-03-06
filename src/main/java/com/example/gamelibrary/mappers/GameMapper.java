package com.example.gamelibrary.mappers;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.entities.Game;

public class GameMapper {

    // map ENTITY -> DTO
    public static GameDTO toDTO(Game game){
        GameDTO dto = new GameDTO();

        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setGenre(game.getGenre());
        dto.setPlatform(game.getPlatform());
        dto.setDeveloper(game.getDeveloper());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setImageUrl(game.getImageUrl());

        return dto;
    }


    // map DTO -> ENTITY
    public static Game toEntity(CreateGameDTO dto) {

        Game game = new Game();

        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setPlatform(dto.getPlatform());
        game.setDeveloper(dto.getDeveloper());
        game.setReleaseDate(dto.getReleaseDate());
        game.setImageUrl(dto.getImageUrl());
        game.setDescription(dto.getDescription());

        return game;
    }


    public static void updateEntity(Game game, UpdateGameDTO dto) {

        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setPlatform(dto.getPlatform());
        game.setDeveloper(dto.getDeveloper());
        game.setReleaseDate(dto.getReleaseDate());
        game.setImageUrl(dto.getImageUrl());
        game.setDescription(dto.getDescription());
    }
}
