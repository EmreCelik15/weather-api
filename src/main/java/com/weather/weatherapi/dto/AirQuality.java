package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQuality {
    private String co;
    private String no2;
    private String o3;
    private String so2;
    private String pm25;
    private Integer usEpaIndex;
    private Integer gbDefraIndex;

    public String getCo() {
        return co;
    }

    public String getNo2() {
        return no2;
    }

    public String getO3() {
        return o3;
    }

    public String getSo2() {
        return so2;
    }

    public String getPm25() {
        return pm25;
    }

    public Integer getUsEpaIndex() {
        return usEpaIndex;
    }

    public Integer getGbDefraIndex() {
        return gbDefraIndex;
    }
}
