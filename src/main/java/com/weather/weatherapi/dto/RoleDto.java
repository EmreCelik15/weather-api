package com.weather.weatherapi.dto;

import com.weather.weatherapi.model.Role;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public record RoleDto(Long id, String name, LocalDateTime createdDate, Long createdBy) {
    public static RoleDto convertToRoleDto(Role from) {
        return new RoleDto(from.getId(), from.getName(), from.getCreatedDate(), from.getCreatedBy());
    }

    public static Page<RoleDto> converToAllRoleDto(Page<Role> roles) {
        return roles.map(RoleDto::convertToRoleDto);
    }

    ;
}

