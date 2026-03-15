package com.example.gamelibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyGamesController {
    @GetMapping("/my-games")
    public String myGames(Model model){

        model.addAttribute("content", "pages/my-games");

        return "layout/main";
    }
}
