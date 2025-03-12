package com.weather.weatherapi.dto;

import com.weather.weatherapi.model.Role;

import java.time.LocalDateTime;

public record RoleDto(Long Id, String name, LocalDateTime createdDate, Long userId) {
    public static RoleDto convertToRoleDto(Role from) {
        return new RoleDto(from.getId(), from.getName(), from.getCreatedDate(), from.getCreatedBy());
    }
}
