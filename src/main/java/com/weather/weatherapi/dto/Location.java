package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
public class Location {
    private String name;
    private String country;
    private String region;
    private Double lat;
    private Double lon;
    @JsonProperty("timezone_id")
    private String timezoneId;
    private String localtime;
    @JsonProperty("localtime_epoch")
    private String localtimeEpoch;
    @JsonProperty("utc_offset")
    private String utcOffset;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getLocalTime() {
        return localtime;
    }

    public String getRegion() {
        return region;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public String getLocaltime() {
        return localtime;
    }

    public String getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public String getUtcOffset() {
        return utcOffset;
    }
}
