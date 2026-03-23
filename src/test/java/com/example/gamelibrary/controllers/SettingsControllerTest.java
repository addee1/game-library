package com.example.gamelibrary.controllers;

import com.example.gamelibrary.services.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(SettingsController.class)
class SettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Test
    @DisplayName("Should return settings page with games and form data")
    void settings_shouldReturnPage() throws Exception {

        when(gameService.getAllGames()).thenReturn(List.of());

        mockMvc.perform(get("/settings"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"))
                .andExpect(model().attributeExists("games"))
                .andExpect(model().attributeExists("createGameDTO"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attribute("content", "pages/settings"));
    }

    @Test
    @DisplayName("Should return page when create game validation fails")
    void createGame_shouldReturnPage_whenValidationFails() throws Exception {

        mockMvc.perform(post("/games")
                        .param("title", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"));
    }

    @Test
    @DisplayName("Should return page when update game validation fails")
    void updateGame_shouldReturnPage_whenValidationFails() throws Exception {

        mockMvc.perform(post("/games/1")
                        .param("title", "") // invalid
                        .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"));
    }

    @Test
    @DisplayName("Should redirect to settings page after deleting a game")
    void deleteGame_shouldRedirect() throws Exception {

        mockMvc.perform(post("/games/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings"));
    }
}