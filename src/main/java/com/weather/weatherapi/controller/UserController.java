package com.weather.weatherapi.controller;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public GenericResponse<UserDto> addUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return userService.addUserRole(userId, roleId);
    }
    @PostMapping("/add")
    public GenericResponse<UserDto> addUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return userService.addUser(userRegistrationRequest);
    }

    @GetMapping("/getAll")
    public GenericResponse<Page<UserDto>> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id,asc") String sort) {
        Sort.Direction direction = sort.endsWith(",desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String property = sort.split(",")[0];
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/getUser/{userId}")
    public GenericResponse<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
    @DeleteMapping("/delete/{userId}")
    public GenericResponse<UserDto> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
