package com.example.gamelibrary.controllers;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.services.GameService;
import com.example.gamelibrary.services.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameDetailsController.class)
public class GameDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @MockitoBean
    private ReviewService reviewService;

    @Test
    @DisplayName("Should return game details page with reviews and average rating")
    void gameDetails_shouldReturnPage() throws Exception {

        GameDTO game = new GameDTO();
        game.setId(1L);
        game.setTitle("Test Game");

        when(gameService.getGameById(1L)).thenReturn(game);
        when(reviewService.getReviewsForGame(1L)).thenReturn(List.of());
        when(reviewService.getAverageRating(1L)).thenReturn(4.0);


        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"))

                .andExpect(model().attributeExists("game"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(model().attributeExists("createReviewDTO"))
                .andExpect(model().attributeExists("averageRating"))

                .andExpect(model().attribute("game", game))
                .andExpect(model().attribute("content", "pages/game-details"));
    }

    @Test
    @DisplayName("Should return page when review validation fails")
    void addReview_shouldReturnPage_whenValidationFails() throws Exception {

        when(gameService.getGameById(1L)).thenReturn(new GameDTO());
        when(reviewService.getReviewsForGame(1L)).thenReturn(List.of());
        when(reviewService.getAverageRating(1L)).thenReturn(0.0);

        mockMvc.perform(post("/games/1/reviews")
                        .param("username", "") // invalid (triggar validation)
                        .param("rating", "5")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("layout/main"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(model().attributeExists("createReviewDTO"));
    }

    @Test
    @DisplayName("Should redirect to game page when review is successfully submitted")
    void addReview_shouldRedirect_whenValid() throws Exception {

        mockMvc.perform(post("/games/1/reviews")
                        .param("username", "Adam")
                        .param("rating", "5")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games/1?success=true"));
    }

    @Test
    @DisplayName("Should include correct average rating in model")
    void gameDetails_shouldHandleReviews() throws Exception {

        GameDTO game = new GameDTO();
        game.setId(1L);

        when(gameService.getGameById(1L)).thenReturn(game);
        when(reviewService.getReviewsForGame(1L)).thenReturn(List.of());
        when(reviewService.getAverageRating(1L)).thenReturn(5.0);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("averageRating", 5.0));
    }
}