package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Astro {
    @JsonProperty("sunrise")
    private String sunRise;
    @JsonProperty("sunset")
    private String sunSet;
    @JsonProperty("moonrise")
    private String moonRise;
    @JsonProperty("moonset")
    private String moonSet;
    @JsonProperty("moon_phase")
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
