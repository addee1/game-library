package com.example.gamelibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameDetailsController {

    @GetMapping("/games/{id}")
    public String gameDetails(Model model){

        model.addAttribute("content", "pages/game-details");

        return "layout/main";
    }
}
