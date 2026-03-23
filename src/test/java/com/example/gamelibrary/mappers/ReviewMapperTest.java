package com.example.gamelibrary.mappers;

import com.example.gamelibrary.dtos.CreateReviewDTO;
import com.example.gamelibrary.entities.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ReviewMapperTest {
    @Test
    @DisplayName("Should map CreateReviewDTO to Review entity")
    void toEntity_shouldMapFields() {
        CreateReviewDTO dto = new CreateReviewDTO();
        dto.setUsername("Adam");
        dto.setRating(5);
        dto.setComment("Nice");

        Review review = ReviewMapper.toEntity(dto);

        assertEquals("Adam", review.getUsername());
        assertEquals(5, review.getRating());
        assertEquals("Nice", review.getComment());
    }

    @Test
    @DisplayName("Should map Review entity to ReviewDTO")
    void toDTO_shouldMapFields() {
        Review review = new Review();
        review.setUsername("Adam");
        review.setRating(5);
        review.setComment("Nice");

        var dto = ReviewMapper.toDTO(review);

        assertEquals("Adam", dto.getUsername());
        assertEquals(5, dto.getRating());
        assertEquals("Nice", dto.getComment());
    }
}
