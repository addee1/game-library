package com.example.gamelibrary.repositories;

import com.example.gamelibrary.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGameIdOrderByCreatedAtDesc(Long gameId);
}
