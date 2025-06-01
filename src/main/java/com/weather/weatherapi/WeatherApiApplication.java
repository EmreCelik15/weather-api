package com.weather.weatherapi;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.weather.weatherapi")
@EnableScheduling
public class WeatherApiApplication {


    public static void main(String[] args) {

        SpringApplication.run(WeatherApiApplication.class, args);

    }
}

