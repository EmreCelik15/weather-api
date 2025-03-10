package com.weather.weatherapi.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.weather.weatherapi.dto.UserDto;

import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserRegistrationRequest request) throws Exception {
        if (userRepository.findByUsername(request.username()).isEmpty() && request.username() != null) {
            User user = new User();
            user.setUsername(request.username());
            user.setPassword(passwordEncoder.encode(request.password()));
            Role defaultRole = roleRepository.findByName("USER").orElseThrow(() -> new Exception("Role Not Found!"));
            user.getRoles().add(defaultRole);
            userRepository.save(user);
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                    String.valueOf(user.getRoles().stream().map(Role::getName).collect(Collectors.toList())),
                    user.getCreatedDate().toString());
        } else {
            User user = userRepository.findByUsername(request.username()).get();
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                    String.valueOf(user.getRoles().stream().map(Role::getName).collect(Collectors.toList())),
                    user.getCreatedDate().toString());
        }
    }

}
