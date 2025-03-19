package com.weather.weatherapi.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final AmqpTemplate rabbitTemplate;

    public MessageProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String city) {
        rabbitTemplate.convertAndSend(
                "weather.exchange",   // Exchange adı
                "weatherRoutingKey", // Routing key
                city              // Mesaj içeriği
        );
        System.out.println("Mesaj gönderildi: " + city);
    }
}
