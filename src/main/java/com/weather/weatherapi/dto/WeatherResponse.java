package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private Request request;
    private Location location;

    private Current current;

    public Request getRequest() {
        return request;
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

}
