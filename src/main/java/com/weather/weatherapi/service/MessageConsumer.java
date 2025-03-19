package com.weather.weatherapi.service;

import com.weather.weatherapi.dto.WeatherDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    public final WeatherService weatherService;

    public MessageConsumer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RabbitListener(queues = "weather.request.queu")
    public void handleWeatherRequest(String city) {
        WeatherDto weatherDto = weatherService.getWeatherByCityName(city);
        System.out.println("Alınan mesaj: " + city);
    }
}
