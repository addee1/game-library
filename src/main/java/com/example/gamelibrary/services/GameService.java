package com.example.gamelibrary.services;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.entities.Game;
import com.example.gamelibrary.mappers.GameMapper;
import com.example.gamelibrary.repositories.GameRepository;
import org.springframework.stereotype.Service;
import com.example.gamelibrary.exceptions.GameNotFoundException;
import com.example.gamelibrary.specifications.GameSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> getAllGames(){

        return gameRepository.findAll()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }

    public GameDTO getGameById(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return GameMapper.toDTO(game);
    }

    public void createGame(CreateGameDTO dto){
        Game game = GameMapper.toEntity(dto);

        gameRepository.save(game);
    }

    public void updateGame(Long id, UpdateGameDTO dto) {

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        GameMapper.updateEntity(game, dto);
        gameRepository.save(game);

    }

    public void deleteGame(Long id) {

        if(!gameRepository.existsById(id)){
            throw new GameNotFoundException(id);
        }
        gameRepository.deleteById(id);
    }

    public List<GameDTO> getAllFeatured(){

        return gameRepository.findByFeaturedTrue()
                .stream()
                .map(GameMapper::toDTO)
                .toList();

    }

    public List<GameDTO> getAllFavorites(){
        return gameRepository.findByFavoriteTrue()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }


    // sort by createdAt desc.
    public List<GameDTO> getAllSortedByNewest() {
        return gameRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(GameMapper::toDTO)
                .toList();
    }


    // get featured for home page
    public List<GameDTO> getFeaturedForHome() {
        return gameRepository.findByFeaturedTrue()
                .stream()
                .limit(6)
                .map(GameMapper::toDTO)
                .toList();
    }

    // get recent for home page
    public List<GameDTO> getRecentForHome() {
        return gameRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .limit(6)
                .map(GameMapper::toDTO)
                .toList();
    }

    public Page<GameDTO> getFilteredGames(
            String search,
            String genre,
            String platform,
            Boolean featured,
            Boolean favorite,
            Pageable pageable
    ) {

        Specification<Game> spec = Specification
                .where(GameSpecification.titleOrDeveloperContains(search))
                .and(GameSpecification.hasGenre(genre))
                .and(GameSpecification.hasPlatform(platform))
                .and(GameSpecification.isFeatured(featured))
                .and(GameSpecification.isFavorite(favorite));

        return gameRepository.findAll(spec, pageable)
                .map(GameMapper::toDTO);
    }

}
