package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.exceptions.GameNotFoundException;
import com.example.gamelibrary.repositories.GameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    @DisplayName("Should return game when ID exists")
    void getGameById_shouldReturnGame() {
        Game game = new Game();
        game.setTitle("Test");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        var result = gameService.getGameById(1L);

        assertEquals("Test", result.getTitle());
    }

    @Test
    @DisplayName("Should throw exception when game not found")
    void getGameById_shouldThrowIfNotFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class,
                () -> gameService.getGameById(1L));
    }

    @Test
    @DisplayName("Should save new game")
    void createGame_shouldSaveGame() {
        CreateGameDTO dto = new CreateGameDTO();
        dto.setTitle("New Game");

        gameService.createGame(dto);

        verify(gameRepository).save(any(Game.class));
    }

    @Test
    @DisplayName("Should update game when ID exists")
    void updateGame_shouldUpdateGame() {
        Game game = new Game();
        game.setTitle("Old");

        UpdateGameDTO dto = new UpdateGameDTO();
        dto.setTitle("New");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        gameService.updateGame(1L, dto);

        assertEquals("New", game.getTitle());
        verify(gameRepository).save(game);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existing game")
    void updateGame_shouldThrowIfNotFound() {
        UpdateGameDTO dto = new UpdateGameDTO();

        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class,
                () -> gameService.updateGame(1L, dto));
    }

    @Test
    @DisplayName("Should delete game when it exists")
    void deleteGame_shouldDeleteIfExists() {
        when(gameRepository.existsById(1L)).thenReturn(true);

        gameService.deleteGame(1L);

        verify(gameRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existing game")
    void deleteGame_shouldThrowIfNotExists() {
        when(gameRepository.existsById(1L)).thenReturn(false);

        assertThrows(GameNotFoundException.class,
                () -> gameService.deleteGame(1L));
    }

    @Test
    @DisplayName("Should return filtered games page")
    void getFilteredGames_shouldReturnPage() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Game> page = new PageImpl<>(List.of(new Game()));

        when(gameRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(page);

        var result = gameService.getFilteredGames(
                "test",
                "RPG",
                "PC",
                true,
                false,
                pageable
        );

        assertNotNull(result);
        verify(gameRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    @DisplayName("Should handle null filters when fetching games")
    void getFilteredGames_shouldHandleNullFilters() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Game> page = new PageImpl<>(List.of());

        when(gameRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(page);

        var result = gameService.getFilteredGames(
                null,
                null,
                null,
                null,
                null,
                pageable
        );

        assertNotNull(result);
    }
    @Test
    @DisplayName("Should return empty page when no games match filters")
    void getFilteredGames_shouldReturnEmptyPage() {

        Pageable pageable = PageRequest.of(0, 10);

        when(gameRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(Page.empty());

        var result = gameService.getFilteredGames(
                "x",
                null,
                null,
                null,
                null,
                pageable
        );

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return all games")
    void getAllGames_shouldReturnList() {

        Game game = new Game();
        game.setTitle("Test");

        when(gameRepository.findAll()).thenReturn(List.of(game));

        var result = gameService.getAllGames();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return featured games")
    void getAllFeatured_shouldReturnList() {

        Game game = new Game();

        when(gameRepository.findByFeaturedTrue()).thenReturn(List.of(game));

        var result = gameService.getAllFeatured();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return favorite games")
    void getAllFavorites_shouldReturnList() {

        Game game = new Game();

        when(gameRepository.findByFavoriteTrue()).thenReturn(List.of(game));

        var result = gameService.getAllFavorites();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return games sorted by newest")
    void getAllSortedByNewest_shouldReturnList() {

        Game game = new Game();

        when(gameRepository.findAllByOrderByCreatedAtDesc())
                .thenReturn(List.of(game));

        var result = gameService.getAllSortedByNewest();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should limit featured games to six for home page")
    void getFeaturedForHome_shouldLimitToSix() {

        List<Game> games = List.of(
                new Game(), new Game(), new Game(),
                new Game(), new Game(), new Game(), new Game()
        );

        when(gameRepository.findByFeaturedTrue()).thenReturn(games);

        var result = gameService.getFeaturedForHome();

        assertEquals(6, result.size()); // viktig!
    }

    @Test
    @DisplayName("Should limit recent games to six for home page")
    void getRecentForHome_shouldLimitToSix() {

        List<Game> games = List.of(
                new Game(), new Game(), new Game(),
                new Game(), new Game(), new Game(), new Game()
        );

        when(gameRepository.findAllByOrderByCreatedAtDesc())
                .thenReturn(games);

        var result = gameService.getRecentForHome();

        assertEquals(6, result.size());
    }
}