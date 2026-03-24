package com.example.gamelibrary.controllers;

import com.example.gamelibrary.configs.GenreConstants;
import com.example.gamelibrary.configs.PlatformConstants;
import com.example.gamelibrary.dtos.GameDTO;
import com.example.gamelibrary.services.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AllGamesController {
    private final GameService gameService;

    public AllGamesController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/games")
    public String games(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) String priceSort,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "14") int size,
            Model model
    ) {

        Sort sort = Sort.by(
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("id")
        );

        if ("low".equals(priceSort)) {
            sort = Sort.by(
                    Sort.Order.asc("price"),
                    Sort.Order.asc("id")
            );
        } else if ("high".equals(priceSort)) {
            sort = Sort.by(
                    Sort.Order.desc("price"),
                    Sort.Order.asc("id")
            );
        }

        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 100);

        Pageable pageable = PageRequest.of(safePage, safeSize, sort);

        Page<GameDTO> gamePage = gameService.getFilteredGames(
                search,
                genre,
                platform,
                featured,
                null,
                pageable
        );

        model.addAttribute("games", gamePage.getContent());
        model.addAttribute("currentPage", gamePage.getNumber());
        model.addAttribute("totalPages", gamePage.getTotalPages());

        model.addAttribute("search", search);
        model.addAttribute("featured", featured);
        model.addAttribute("genre", genre);
        model.addAttribute("priceSort", priceSort);
        model.addAttribute("genres", GenreConstants.GENRES);
        model.addAttribute("platforms", PlatformConstants.PLATFORMS);
        model.addAttribute("platform", platform);

        model.addAttribute("content", "pages/games");

        return "layout/main";
    }
}
