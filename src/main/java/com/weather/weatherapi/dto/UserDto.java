package com.weather.weatherapi.dto;


import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public record UserDto(Long Id, String username, String password, String passwordNoHash, String role,
                      String createdDate) {

    public static UserDto convert(User from) {
        return new UserDto(from.getId(), from.getUsername(), from.getPassword(), from.getPasswordNoHash(), String.valueOf(from.getRoles().stream().map(
                Role::getName).collect(Collectors.toList())), from.getCreatedDate().toString());
    }
}
