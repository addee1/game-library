package com.example.gamelibrary.repositories;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByFeaturedTrue();
    List<Game> findByFavoriteTrue();

    // Should get the 6 latest added games
    List<Game> findTop6ByOrderByCreatedAtDesc();
}
