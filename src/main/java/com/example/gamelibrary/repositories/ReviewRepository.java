package com.example.gamelibrary.repositories;

import com.example.gamelibrary.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGameIdOrderByCreatedAtDesc(Long gameId);

    @Query("select avg(r.rating) from Review r where r.game.id = :gameId")
    Double findAverageRatingByGameId(@Param("gameId") Long gameId);
}
