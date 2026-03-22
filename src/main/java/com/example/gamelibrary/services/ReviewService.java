package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateReviewDTO;
import com.example.gamelibrary.dtos.ReviewDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.entities.Review;
import com.example.gamelibrary.exceptions.GameNotFoundException;
import com.example.gamelibrary.mappers.ReviewMapper;
import com.example.gamelibrary.repositories.GameRepository;
import com.example.gamelibrary.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;

    public ReviewService(ReviewRepository reviewRepository, GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.gameRepository = gameRepository;
    }

    public void addReview(Long gameId, CreateReviewDTO dto){

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));

        Review review = ReviewMapper.toEntity(dto);

        review.setUsername(dto.getUsername().trim());
        review.setComment(dto.getComment() != null ? dto.getComment().trim() : null);

        review.setGame(game);

        reviewRepository.save(review);
    }

    public List<ReviewDTO> getReviewsForGame(Long gameId){
        return reviewRepository.findByGameIdOrderByCreatedAtDesc(gameId)
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    public double getAverageRating(Long gameId){
        List<Review> reviews = reviewRepository.findByGameIdOrderByCreatedAtDesc(gameId);

        if(reviews.isEmpty()){
            return 0.0;
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
