package com.weather.weatherapi.controller;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.RoleDto;
import com.weather.weatherapi.service.RoleService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role")
@Validated
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public GenericResponse<RoleDto> addRole(@NotBlank String name,
                                            @Positive(message = "ID pozitif bir sayı olmalıdır!") Long userId) {
        return roleService.addRole(name, userId);
    }

    @GetMapping("/getAll")
    public GenericResponse<Page<RoleDto>> getAllRole(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id,asc") String sort) {
        Sort.Direction direction = sort.endsWith(",desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String property = sort.split(",")[0];
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return roleService.getAllRole(pageable);
    }

    @GetMapping("/get/{id}")
    public GenericResponse<RoleDto> getRole(@PathVariable @Positive(message = "ID pozitif bir sayı olmalıdır!") Long id) {
        return roleService.getRole(id);
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<RoleDto> deleteRole(@PathVariable @Positive(message = "ID pozitif bir sayı olmalıdır!") Long id) {
        return roleService.deleteRole(id);
    }
}
