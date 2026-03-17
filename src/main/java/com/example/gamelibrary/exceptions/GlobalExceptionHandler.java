package com.example.gamelibrary.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)
    public String handleGameNotFound(GameNotFoundException ex, Model model){

        model.addAttribute("errorMessage", ex.getMessage());

        return "error/404";
    }
}
