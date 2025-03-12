package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.RoleDto;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.service.RoleService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/role")
@Validated
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<RoleDto> addRole(@NotBlank String name,
                                           @Positive(message = "ID pozitif bir sayı olmalıdır!") Long userId) {
        return ResponseEntity.ok(roleService.addRole(name, userId));
    }
}
