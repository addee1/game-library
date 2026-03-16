package com.example.gamelibrary.controllers;

import com.example.gamelibrary.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllGamesController {
    private final GameService gameService;

    public AllGamesController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/games")
    public String games(Model model){

        model.addAttribute("games", gameService.getAllGames());
        model.addAttribute("content", "pages/games");

        return "layout/main";
    }
}
