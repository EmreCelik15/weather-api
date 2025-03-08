package com.weather.weatherapi.dto;

public record UserRegistrationRequest(
        String username,
        String password
) {}
