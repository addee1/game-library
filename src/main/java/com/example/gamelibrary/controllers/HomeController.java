package com.example.gamelibrary.controllers;

import com.example.gamelibrary.configs.GenreConstants;
import com.example.gamelibrary.models.GenreCard;
import com.example.gamelibrary.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final GameService gameService;

    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("featuredGames", gameService.getFeaturedForHome());
        model.addAttribute("recentGames", gameService.getRecentForHome());
        model.addAttribute("homeGenres", GenreConstants.HOME_GENRES);


        model.addAttribute("content", "pages/home");

        return "layout/main";
    }

}
