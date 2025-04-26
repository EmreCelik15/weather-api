package com.weather.weatherapi.dto;


import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public record UserDto(Long Id, String username, String password, String passwordNoHash, String role,
                      String createdDate, String lastLoginDate) {
    static String dateTimeString = "2023/10/15 14:30:45";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

    public static UserDto convertUserToUserDto(User from) {
        return new UserDto(from.getId(), from.getUsername(), from.getPassword(), from.getPasswordNoHash(), String.valueOf(from.getRoles().stream().map(
                Role::getName).collect(Collectors.toList())), from.getCreatedDate() == null ? null : from.getCreatedDate().toString()
                , from.getLastLoginDate() == null ? null : from.getLastLoginDate().toString());
    }

    public static Page<UserDto> converToAllUserDto(Page<User> users) {
        return users.map(UserDto::convertUserToUserDto);
    }
}
