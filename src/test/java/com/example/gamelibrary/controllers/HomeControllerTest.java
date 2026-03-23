package com.example.gamelibrary.controllers;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.services.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Test
    @DisplayName("Should return home page with featured and recent games")
    void home_shouldReturnPage() throws Exception {

        when(gameService.getFeaturedForHome()).thenReturn(List.of());
        when(gameService.getRecentForHome()).thenReturn(List.of());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"))
                .andExpect(model().attributeExists("featuredGames"))
                .andExpect(model().attributeExists("recentGames"))
                .andExpect(model().attributeExists("homeGenres"))
                .andExpect(model().attribute("content", "pages/home"));
    }
}