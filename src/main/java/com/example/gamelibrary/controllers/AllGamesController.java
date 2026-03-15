package com.example.gamelibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllGamesController {

    @GetMapping("/games")
    public String games(Model model){

        model.addAttribute("content", "pages/games");

        return "layout/main";
    }
}
