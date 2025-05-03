package com.weather.weatherapi.service;

import com.rabbitmq.client.*;
import com.weather.weatherapi.dto.WeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageConsumer {
    private final static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    public final WeatherService weatherService;

    public MessageConsumer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RabbitListener(queues = "weather.request.queu")
    public void handleWeatherRequest(String city, Channel channel, Message message) {

        try {
            WeatherDto weatherDto = weatherService.getWeatherByCityName(city);
            if (weatherDto == null) {
                // API çağrısı başarısızsa veya fallback çalıştıysa, burada gereken işlemi yapıyoruz
                logger.warn("Hava durumu bilgisi getirilemedi", city);
            } else {
                System.out.println("Alınan mesaj:" + city);
            }
            // Başarılı şekilde işlendiyse mesajı onaylıyoruz
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("Alınan mesaj: " + city);
        } catch (Exception e) {
            logger.error("Mesaj işlenirken hata oluştu.", e);
            try {
                // Hata durumunda mesajı yeniden kuyruğa almamakk için basicNack
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}
