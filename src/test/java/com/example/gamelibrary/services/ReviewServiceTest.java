package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateReviewDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.entities.Review;
import com.example.gamelibrary.exceptions.GameNotFoundException;
import com.example.gamelibrary.repositories.GameRepository;
import com.example.gamelibrary.repositories.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    @DisplayName("Should add review to game and save it")
    void addReview_shouldSaveReview() {
        Game game = new Game();

        CreateReviewDTO dto = new CreateReviewDTO();
        dto.setUsername("Adam");
        dto.setRating(5);
        dto.setComment("Nice game");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        reviewService.addReview(1L, dto);

        verify(gameRepository).save(game);

        assertEquals(1, game.getReviews().size());
        Review review = game.getReviews().get(0);

        assertEquals("Adam", review.getUsername());
        assertEquals("Nice game", review.getComment());
        assertEquals(5, review.getRating());
    }

    @Test
    @DisplayName("Should throw exception when adding review to non-existing game")
    void addReview_shouldThrowIfGameNotFound() {
        CreateReviewDTO dto = new CreateReviewDTO();

        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class,
                () -> reviewService.addReview(1L, dto));
    }

    @Test
    @DisplayName("Should return list of reviews for a game")
    void getReviewsForGame_shouldReturnList() {
        when(reviewRepository.findByGameIdOrderByCreatedAtDesc(1L))
                .thenReturn(List.of(new Review()));

        var result = reviewService.getReviewsForGame(1L);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return correct average rating")
    void getAverageRating_shouldReturnAverage() {
        when(reviewRepository.findAverageRatingByGameId(1L))
                .thenReturn(4.5);

        double result = reviewService.getAverageRating(1L);

        assertEquals(4.5, result);
    }

    @Test
    @DisplayName("Should return 0 when average rating is null")
    void getAverageRating_shouldReturnZeroIfNull() {
        when(reviewRepository.findAverageRatingByGameId(1L))
                .thenReturn(null);

        double result = reviewService.getAverageRating(1L);

        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("Should return 0 when no reviews exist")
    void getAverageRating_shouldReturnZeroIfNoReviews() {
        when(reviewRepository.findAverageRatingByGameId(1L)).thenReturn(null);

        double result = reviewService.getAverageRating(1L);

        assertEquals(0.0, result);
    }
}