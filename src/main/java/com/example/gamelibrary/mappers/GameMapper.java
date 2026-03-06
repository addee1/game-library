package com.example.gamelibrary.mappers;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.entities.Game;

public class GameMapper {
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
}
