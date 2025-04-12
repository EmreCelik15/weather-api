package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
public class Current {
    @JsonProperty("observation_time")
    private String observationTime;
    private Integer temperature;
    @JsonProperty("weather_code")
    private Integer weatherCode;
    @JsonProperty("weather_icons")
    private List<String> weatherIcons;
    @JsonProperty("weather_descriptions")
    private List<String> weatherDescriptions;
    @JsonProperty("astro")
    private Astro astro;
    @JsonProperty("wind_speed")
    private Integer windSpeed;
    @JsonProperty("wind_degree")
    private Integer windDegree;
    @JsonProperty("wind_dir")
    private String windDirection;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    @JsonProperty("cloudcover")
    private String cloudCover;
    @JsonProperty("feelslike")
    private String feelsLike;
    @JsonProperty("uv_index")
    private String uvIndex;
    private String visibility;
    @JsonProperty("is_day")
    private String isDay;

    public String getObservationTime() {
        return observationTime;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public List<String> getWeatherIcons() {
        return weatherIcons;
    }

    public List<String> getWeatherDescriptions() {
        return weatherDescriptions;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getPrecip() {
        return precip;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getIsDay() {
        return isDay;
    }
}
