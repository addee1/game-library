package com.example.gamelibrary.controllers;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.services.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AllGamesController.class)
class AllGamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Test
    @DisplayName("Should return games page with filtered results")
    void games_shouldReturnPage() throws Exception {

        GameDTO game = new GameDTO();
        game.setId(1L);
        game.setTitle("Test Game");

        Page<GameDTO> page = new PageImpl<>(List.of(game));

        when(gameService.getFilteredGames(
                any(),
                any(),
                any(),
                any(),
                isNull(),
                any()
        )).thenReturn(page);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"))
                .andExpect(model().attributeExists("games"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attribute("content", "pages/games"));
    }
}