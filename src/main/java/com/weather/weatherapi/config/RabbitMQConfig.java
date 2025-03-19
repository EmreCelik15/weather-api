package com.weather.weatherapi.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue Tanımla
    @Bean
    public Queue weatherRequestQueue() {
        return new Queue("weather.request.queu", true); // Durable queue
    }

    // Exchange Tanımla (Direct Exchange)
    @Bean
    public DirectExchange weatherExchange() {
        return new DirectExchange("weather.exchange");
    }

    // Binding Tanımla (Queue ↔ Exchange)
    @Bean
    public Binding binding(Queue exampleQueue, DirectExchange exampleExchange) {
        return BindingBuilder
                .bind(weatherRequestQueue())
                .to(weatherExchange())
                .with("weatherRoutingKey");
    }
}
