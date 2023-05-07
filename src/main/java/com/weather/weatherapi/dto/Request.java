package com.weather.weatherapi.dto;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
public class Request {
    private String type;
    private String query;
    private String language;
    private String unit;

    public String getType() {
        return type;
    }

    public String getQuery() {
        return query;
    }

    public String getLanguage() {
        return language;
    }

    public String getUnit() {
        return unit;
    }
}
