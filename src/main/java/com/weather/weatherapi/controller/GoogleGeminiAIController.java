package com.weather.weatherapi.controller;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.service.GoogleGeminiAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/ai")
public class GoogleGeminiAIController {
    private final GoogleGeminiAIService googleGeminiAIService;

    public GoogleGeminiAIController(GoogleGeminiAIService googleGeminiAIService) {
        this.googleGeminiAIService = googleGeminiAIService;
    }

    @GetMapping("/recipe")
    public ResponseEntity<GenericResponse<String>> getRecipe(@RequestParam String message) {
        return ResponseEntity.ok(googleGeminiAIService.getRecipeForDinner(message));
    }
}
