package com.weather.weatherapi.dto;

public record LoginResponse(String username, String password, String passwordNoHash, String jwt) {
}
