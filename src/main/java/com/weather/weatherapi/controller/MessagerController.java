package com.weather.weatherapi.controller;

import com.weather.weatherapi.service.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rabbit")
public class MessagerController {

    private final MessageProducer messageProducer;

    public MessagerController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageProducer.requestWeather(message);
        return "Mesaj gönderildi: " + message;
    }
}
