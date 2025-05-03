package com.weather.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.constants.Constants;
import com.weather.weatherapi.dto.WeatherDto;
import com.weather.weatherapi.dto.WeatherResponse;
import com.weather.weatherapi.model.Weather;
import com.weather.weatherapi.repository.WeatherRepository;
import jakarta.validation.constraints.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
                return WeatherDto.convert(getWeatherFromWeatherStack(city).getBody().getData());
            }
            return WeatherDto.convert(weather);
        }).orElseGet(() -> WeatherDto.convert(getWeatherFromWeatherStack(city).getBody().getData()));
    }

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "15000")
    public void clearCache() {
        logger.info("Cache cleared.");
    }

    private ResponseEntity<GenericResponse<Weather>> getWeatherFromWeatherStack(String city) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity
                (Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY
                        + Constants.QUERY_KEY_PARAM + city, String.class);
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            logger.info("Weather Response: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getBody()));
            return ResponseEntity.ok().body(new GenericResponse<>(true, city
                    + " şehrine ait güncel hava durumu bilgisi getirildi.", saveWeather(city, weatherResponse).getData(),
                    HttpStatus.OK));
        } catch (JsonProcessingException e) {
            logger.error("JSON Parse Hatası", e);
            return ResponseEntity.badRequest().body(new GenericResponse<>(false, city
                    + " şehrine ait güncel hava durumu bilgisi getirilemedi. Bir hata oluştu " + "Hata mesajı:" +
                    e.getMessage(), null, HttpStatus.BAD_REQUEST));
        }
    }

    private GenericResponse<Weather> saveWeather(String city, WeatherResponse weatherResponse) {
        try {
            Weather weather = new Weather(
                    city,
                    weatherResponse.getLocation().getName(),
                    weatherResponse.getLocation().getCountry(),
                    weatherResponse.getCurrent().getTemperature(),
                    LocalDateTime.now(),
                    LocalDateTime.parse(weatherResponse.getLocation().getLocalTime(), DateTimeFormatterService.dateTimeFormatterToDetailTime),
                    LocalTime.parse(weatherResponse.getCurrent().getAstro().getSunRise(), DateTimeFormatterService.dateTimeFormatterToRiseAndSetDate),
                    LocalTime.parse(weatherResponse.getCurrent().getAstro().getSunSet(), DateTimeFormatterService.dateTimeFormatterToRiseAndSetDate),
                    LocalTime.parse(weatherResponse.getCurrent().getAstro().getMoonRise(), DateTimeFormatterService.dateTimeFormatterToRiseAndSetDate),
                    LocalTime.parse(weatherResponse.getCurrent().getAstro().getMoonSet(), DateTimeFormatterService.dateTimeFormatterToRiseAndSetDate),
                    weatherResponse.getCurrent().getAstro().getMoonPhase(),
                    weatherResponse.getCurrent().getAirQuality().getCo());
            return new GenericResponse<>(true, "", weatherRepository.save(weather), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new GenericResponse<>(false, city
                    + "şehrine ait hava Durumu Bilgisi Kaydedilemedi", null, HttpStatus.OK);
        }
    }
}

