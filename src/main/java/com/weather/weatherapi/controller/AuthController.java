package com.weather.weatherapi.controller;

import com.weather.weatherapi.authentication.JwtService;
import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.LoginRequest;
import com.weather.weatherapi.dto.LoginResponse;
import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = authService.loginUser(request);
        ResponseCookie cookie = ResponseCookie.from("jwt_token", loginResponse.jwt())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600)
                .sameSite("Strict").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserDto>> register(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {
        return authService.registerUser(userRegistrationRequest);
    }
}

