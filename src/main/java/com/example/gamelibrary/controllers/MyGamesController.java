package com.example.gamelibrary.controllers;

import com.example.gamelibrary.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyGamesController {

    private final GameService gameService;

    public MyGamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/my-games")
    public String myGames(Model model){

        model.addAttribute("games", gameService.getAllFavorites());
        model.addAttribute("content", "pages/my-games");

        return "layout/main";
    }
}
