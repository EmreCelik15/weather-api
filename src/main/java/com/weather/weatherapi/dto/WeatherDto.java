package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weather.weatherapi.model.Weather;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
public class WeatherDto {
    private String cityName;
    private String country;
    private Integer temperature;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedTime;
    public static WeatherDto convert(Weather from){
        return new WeatherDto(from.getCountry(),from.getCityName(),from.getTemperature(),from.getUpdatedTime());
    }

    public WeatherDto(String cityName, String country, Integer temperature,LocalDateTime updatedTime) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime=updatedTime;
    }

    public WeatherDto() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
