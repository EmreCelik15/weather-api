package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weather.weatherapi.model.Weather;


import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalTime sunRise;
    private LocalTime sunSet;
    private LocalTime moonRise;
    private LocalTime moonSet;
    private String moonPhase;
    private String carbonMonoxide;

    public static WeatherDto convert(Weather from) {
        return new WeatherDto(from.getCountry(), from.getCityName(), from.getTemperature(), from.getUpdatedTime(),
                from.getSunRise(),
                from.getSunSet(), from.getMoonRise(), from.getMoonSet(), from.getMoonPhase(), from.getCarbonMonoxide());
    }

    public WeatherDto(String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalTime sunRise,
                      LocalTime sunSet, LocalTime moonRise, LocalTime moonSet, String moonPhase, String carbonMonoxide) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.moonRise = moonRise;
        this.moonSet = moonSet;
        this.moonPhase = moonPhase;
        this.carbonMonoxide = carbonMonoxide;
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

    public LocalTime getSunRise() {
        return sunRise;
    }

    public void setSunRise(LocalTime sunRise) {
        this.sunRise = sunRise;
    }

    public LocalTime getSunSet() {
        return sunSet;
    }

    public void setSunSet(LocalTime sunSet) {
        this.sunSet = sunSet;
    }

    public LocalTime getMoonRise() {
        return moonRise;
    }

    public void setMoonRise(LocalTime moonRise) {
        this.moonRise = moonRise;
    }

    public LocalTime getMoonSet() {
        return moonSet;
    }

    public void setMoonSet(LocalTime moonSet) {
        this.moonSet = moonSet;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getCarbonMonoxide() {
        return carbonMonoxide;
    }

    public void setCarbonMonoxide(String carbonMonoxide) {
        this.carbonMonoxide = carbonMonoxide;
    }
}