package com.weather.weatherapi.service;


import com.weather.weatherapi.authentication.JwtService;
import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.LoginRequest;
import com.weather.weatherapi.dto.LoginResponse;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.weather.weatherapi.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public ResponseEntity<GenericResponse<UserDto>> registerUser(UserRegistrationRequest request) throws Exception {
        if (!userRepository.findByUsername(request.username()).isPresent()) {
            User user = new User();
            user.setUsername(request.username());
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setPasswordNoHash(request.password());
            Role defaultRole = roleRepository.findByName("USER").orElseThrow(() -> new Exception("Role Not Found!"));
            user.getRoles().add(defaultRole);
            userRepository.save(user);
            return ResponseEntity.ok().body(new GenericResponse<>(true, "Kullanıcı Kaydedildi.", UserDto.convertUserToUserDto(user),
                    HttpStatus.OK)) ;
        } else {
            User user = userRepository.findByUsername(request.username()).get();
            return ResponseEntity.badRequest().body(new GenericResponse<>(false, "Bu kullanıcı adı kullanılmaktadır.", UserDto.convertUserToUserDto(user),
                    HttpStatus.BAD_REQUEST)) ;
        }
    }

    @Transactional
    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.username());
        if (user.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(), request.password()
            ));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken(userDetails);
            user.get().setLastLoginDate(LocalDateTime.now());
            userRepository.save(user.get());
            return new LoginResponse(userDetails.getUsername(), userDetails.getPassword(), request.password(), jwt);
        } else {
            throw new UsernameNotFoundException("Kullanıcı Bulunamadı.");
        }

    }

}
