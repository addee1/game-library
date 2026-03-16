package com.example.gamelibrary.controllers;

import com.example.gamelibrary.dtos.CreateGameDTO;
import com.example.gamelibrary.dtos.UpdateGameDTO;
import com.example.gamelibrary.services.GameService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {

    private final GameService gameService;

    public SettingsController(GameService gameService) {
        this.gameService = gameService;
    }

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        model.addAttribute("createGameDTO", new CreateGameDTO());
    }

    @GetMapping("/settings")
    public String settings(Model model){

        model.addAttribute("content", "pages/settings");

        return "layout/main";
    }

    @PostMapping("/games")
    public String createGame(@Valid @ModelAttribute CreateGameDTO dto, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("openAddModal", true);
            model.addAttribute("content", "pages/settings");

            return "layout/main";
        }

        gameService.createGame(dto);

        return "redirect:/settings";
    }

    @PostMapping("/games/{id}")
    public String updateGame(@PathVariable Long id, @Valid @ModelAttribute UpdateGameDTO dto, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("content", "pages/settings");
            return "layout/main";
        }

        gameService.updateGame(id, dto);

        return "redirect:/settings";
    }

    @PostMapping("/games/{id}/delete")
    public String deleteGame(@PathVariable Long id){

        gameService.deleteGame(id);
        return "redirect:/settings";
    }
}
