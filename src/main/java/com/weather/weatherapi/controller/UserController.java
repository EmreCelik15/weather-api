package com.weather.weatherapi.controller;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/user")
@RateLimiter(name = "basic")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addrole/{userId}/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse<UserDto> addUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return userService.addUserRole(userId, roleId);
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse<UserDto> addUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return userService.addUser(userRegistrationRequest);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse<Page<UserDto>> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id,asc") String sort) {
        Sort.Direction direction = sort.endsWith(",desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String property = sort.split(",")[0];
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/getUser/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse<UserDto> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
