package com.example.gamelibrary.controllers;

import com.example.gamelibrary.dtos.CreateReviewDTO;
import com.example.gamelibrary.services.GameService;
import com.example.gamelibrary.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameDetailsController {

    private final GameService gameService;
    private final ReviewService reviewService;

    public GameDetailsController(GameService gameService, ReviewService reviewService) {
        this.gameService = gameService;
        this.reviewService = reviewService;
    }

    @GetMapping("/games/{id}")
    public String gameDetails(@PathVariable Long id, Model model){

        model.addAttribute("game", gameService.getGameById(id));
        model.addAttribute("reviews", reviewService.getReviewsForGame(id));
        model.addAttribute("createReviewDTO", new CreateReviewDTO());
        model.addAttribute("averageRating", reviewService.getAverageRating(id));

        model.addAttribute("content", "pages/game-details");

        return "layout/main";
    }

    @PostMapping("/games/{id}/reviews")
    public String addReview(
            @PathVariable Long id,
            @Valid @ModelAttribute CreateReviewDTO dto,
            BindingResult result,
            Model model
    ){

        if(result.hasErrors()){
            model.addAttribute("game", gameService.getGameById(id));
            model.addAttribute("reviews", reviewService.getReviewsForGame(id));
            model.addAttribute("averageRating", reviewService.getAverageRating(id));
            model.addAttribute("createReviewDTO", dto);

            model.addAttribute("content", "pages/game-details");

            return "layout/main";
        }

        reviewService.addReview(id, dto);

        return "redirect:/games/" + id + "?success=true";
    }
}
