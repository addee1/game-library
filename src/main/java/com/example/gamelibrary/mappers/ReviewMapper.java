package com.example.gamelibrary.mappers;

import com.example.gamelibrary.dtos.CreateReviewDTO;
import com.example.gamelibrary.dtos.ReviewDTO;
import com.example.gamelibrary.entities.Review;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review){
        ReviewDTO dto = new ReviewDTO();

        dto.setId(review.getId());
        dto.setUsername(review.getUsername());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());

        return dto;
    }

    public static Review toEntity(CreateReviewDTO dto){
        Review review = new Review();

        review.setUsername(dto.getUsername());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return review;
    }
}
