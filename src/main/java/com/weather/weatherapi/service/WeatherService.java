package com.weather.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherapi.constants.Constants;
import com.weather.weatherapi.dto.WeatherDto;
import com.weather.weatherapi.dto.WeatherResponse;
import com.weather.weatherapi.model.Weather;
import com.weather.weatherapi.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@Service
@CacheConfig(cacheNames = {"weathers"})
public class WeatherService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    @Cacheable(key = "#city")
    public WeatherDto getWeatherByCityName(String city) {
        logger.info("Brought from api");
        Optional<Weather> optionalWeather = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);
        return optionalWeather.map(weather -> {
            if (weather.getUpdatedTime().isBefore(LocalDateTime.now().minusSeconds(30))) {
                return WeatherDto.convert(getWeatherFromWeatherStack(city));
            }
            return WeatherDto.convert(weather);
        }).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(city)));
    }

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "15000")
    public void clearCache() {
        logger.info("Cache cleared.");
    }

    private Weather getWeatherFromWeatherStack(String city) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity
                (Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_PARAM + city, String.class);
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeather(city, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Weather saveWeather(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Weather weather = new Weather(
                city,
                weatherResponse.getLocation().getName(),
                weatherResponse.getLocation().getCountry(),
                weatherResponse.getCurrent().getTemperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.getLocation().getLocalTime(), dateTimeFormatter));
        return weatherRepository.save(weather);
    }
}

