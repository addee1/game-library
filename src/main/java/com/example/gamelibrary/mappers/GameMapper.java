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
        dto.setGenres(game.getGenres());
        dto.setPlatform(game.getPlatform());
        dto.setDeveloper(game.getDeveloper());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setPrice(game.getPrice());
        dto.setImageUrl(game.getImageUrl());
        dto.setDescription(game.getDescription());

        dto.setFeatured(game.isFeatured());
        dto.setFavorite(game.isFavorite());

        dto.setCreatedAt(game.getCreatedAt());

        return dto;
    }


    // map DTO -> ENTITY
    public static Game toEntity(CreateGameDTO dto) {

        Game game = new Game();

        game.setTitle(dto.getTitle());
        game.setGenres(dto.getGenres());
        game.setPlatform(dto.getPlatform());
        game.setDeveloper(dto.getDeveloper());
        game.setReleaseDate(dto.getReleaseDate());
        game.setPrice(dto.getPrice());
        game.setImageUrl(dto.getImageUrl());
        game.setDescription(dto.getDescription());

        game.setFeatured(dto.getFeatured());

        return game;
    }


    public static void updateEntity(Game game, UpdateGameDTO dto) {

        game.setTitle(dto.getTitle());
        game.setGenres(dto.getGenres());
        game.setPlatform(dto.getPlatform());
        game.setDeveloper(dto.getDeveloper());
        game.setReleaseDate(dto.getReleaseDate());
        game.setPrice(dto.getPrice());
        game.setImageUrl(dto.getImageUrl());
        game.setDescription(dto.getDescription());
        game.setFeatured(dto.getFeatured());
    }
}
