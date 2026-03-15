package com.example.gamelibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {
    @GetMapping("/settings")
    public String settings(Model model){

        model.addAttribute("content", "pages/settings");

        return "layout/main";
    }
}
