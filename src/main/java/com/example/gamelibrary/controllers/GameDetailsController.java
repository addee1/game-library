package com.example.gamelibrary.controllers;

import com.example.gamelibrary.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameDetailsController {

    private final GameService gameService;

    public GameDetailsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games/{id}")
    public String gameDetails(@PathVariable Long id, Model model){


        model.addAttribute("game", gameService.getGameById(id));
        model.addAttribute("content", "pages/game-details");

        return "layout/main";
    }
}
