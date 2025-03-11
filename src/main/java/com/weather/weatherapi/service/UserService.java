package com.weather.weatherapi.service;

import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserDto addUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı'"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Rol Bulunamadı!"));
        if (user.getRoles().contains(role)) {
            throw new RuntimeException("Kullanıcı zaten bu role sahip:" + role);
        }
        UserDto userDto = UserDto.convert(userRepository.save(user));
        return userDto;
    }
}
