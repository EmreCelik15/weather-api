package com.weather.weatherapi.service;


import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import com.weather.weatherapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.weather.weatherapi.dto.UserDto;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserRegistrationRequest request) {
        if (userRepository.findByUsername(request.username()).isEmpty() && request.username() != null) {
            User user = new User();
            user.setUsername(request.username());
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole().toString(),
                    user.getCreatedDate().toString());
        } else {
            User user = userRepository.findByUsername(request.username()).get();
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole().toString(),
                    user.getCreatedDate().toString());
        }
    }

}
