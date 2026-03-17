package com.example.gamelibrary.controllers;

import com.example.gamelibrary.services.GameService;
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
    public String games(@RequestParam(required = false) String filter,
                        Model model){

        if ("featured".equals(filter)) {
            model.addAttribute("games", gameService.getAllFeatured());
        } else if ("recent".equals(filter)) {
            model.addAttribute("games", gameService.getAllSortedByNewest());
        } else {
            model.addAttribute("games", gameService.getAllGames());
        }

        model.addAttribute("content", "pages/games");

        return "layout/main";
    }
}
