package com.example.gamelibrary.mappers;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.entities.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class GameMapperTest {

    @Test
    @DisplayName("Should map CreateGameDTO to Game entity")
    void toEntity_shouldMapFields() {
        CreateGameDTO dto = new CreateGameDTO();
        dto.setTitle("Game");
        dto.setGenres(Set.of("RPG"));

        Game game = GameMapper.toEntity(dto);

        assertEquals("Game", game.getTitle());
        assertTrue(game.getGenres().contains("RPG"));
    }

    @Test
    @DisplayName("Should map Game entity to GameDTO")
    void toDTO_shouldMapFields() {
        Game game = new Game();
        game.setTitle("Game");
        game.setGenres(Set.of("RPG"));

        var dto = GameMapper.toDTO(game);

        assertEquals("Game", dto.getTitle());
        assertTrue(dto.getGenres().contains("RPG"));
    }

    @Test
    @DisplayName("Should update Game entity from UpdateGameDTO")
    void updateEntity_shouldUpdateFields() {
        Game game = new Game();
        game.setTitle("Old");

        var dto = new com.example.gamelibrary.dtos.UpdateGameDTO();
        dto.setTitle("New");

        GameMapper.updateEntity(game, dto);

        assertEquals("New", game.getTitle());
    }
}
