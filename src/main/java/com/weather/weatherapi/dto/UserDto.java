package com.weather.weatherapi.dto;


public record UserDto(Long Id, String username, String password, String Role, String createdDate) {
}
