package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.WeatherDto;
import com.weather.weatherapi.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
//3:02:05 de kalındı.
@RestController
@RequestMapping("/v1/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeatherByCityName(@PathVariable(value = "city") String city) {
        return new ResponseEntity<>(weatherService.getWeatherByCityName(city), HttpStatus.ACCEPTED);
    }
}
