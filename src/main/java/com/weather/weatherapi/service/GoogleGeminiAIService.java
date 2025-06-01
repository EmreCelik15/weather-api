package com.weather.weatherapi.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.weather.weatherapi.common.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GoogleGeminiAIService {

    String apiKey = System.getenv("GOOGLE_API_KEY");

    public GenericResponse<String> getRecipeForDinner(String message) {
        if (apiKeyControl()) {
            Client client = new Client();
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.0-flash",
                    message,
                    null);
            return new GenericResponse<>(true, message,
                    response.candidates().get().get(0).content().get().text(),
                    HttpStatus.OK);
        } else return new GenericResponse<>(false, "Hata: Google Api Key Ortam Değişkeni Ayarlanmamış",
                null,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public Boolean apiKeyControl() {
        if (apiKey == null && apiKey.isEmpty()) {
            System.err.println("Hata: Google Api Key Ortam Değişkeni Ayarlanmamış");
            return false;
        }
        return true;
    }
}
