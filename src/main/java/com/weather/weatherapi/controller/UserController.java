package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/user")
@RateLimiter(name = "basic")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addrole/{userId}/{roleId}")
    public ResponseEntity<UserDto> addUserRole(@PathVariable Long userId, @PathVariable Long roleId){
       return ResponseEntity.ok(userService.addUserRole(userId, roleId));
       }
    }
