package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.WeatherDto;
import com.weather.weatherapi.service.MessageProducer;
import com.weather.weatherapi.service.WeatherService;
import com.weather.weatherapi.validation.CityNameConstraint;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@RestController
@RequestMapping("/v1/api/weather")
@Validated
@RateLimiter(name = "basic")
public class WeatherController {
    private final WeatherService weatherService;
    private final MessageProducer messageProducer;

    public WeatherController(WeatherService weatherService, MessageProducer messageProducer) {
        this.weatherService = weatherService;
        this.messageProducer = messageProducer;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeatherByCityName(@PathVariable(value = "city") @CityNameConstraint @NotBlank
                                                           String city) {
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }

    @GetMapping("/async/{city}")
    public String getWeatherByCityNameAsync(@PathVariable(value = "city") @CityNameConstraint @NotBlank
                                            String city) {
        messageProducer.requestWeather(city);
        return "İstek kuyruğa alındı. İşlem tamamlandı!";
    }
}
