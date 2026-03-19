package com.example.gamelibrary.repositories;

import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    List<Game> findByFeaturedTrue();
    List<Game> findByFavoriteTrue();
    List<Game> findAllByOrderByCreatedAtDesc();
}
