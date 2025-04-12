package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Astro {
    private String sunRise;
    private String sunSet;
    private String moonRise;
    private String moonSet;
    private String moonPhase;
    private Integer moonIllumination;

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getMoonRise() {
        return moonRise;
    }

    public String getMoonSet() {
        return moonSet;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public Integer getMoonIllumination() {
        return moonIllumination;
    }
}
