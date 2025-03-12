package com.weather.weatherapi.service;

import com.weather.weatherapi.dto.RoleDto;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public RoleDto addRole(String name, Long userId) {
        if (roleRepository.existsByName(name) && name != null && !name.isEmpty()) {
            throw new RuntimeException("Role zaten kayıtlı!");
        }
        if (name == null && name.isEmpty()) {
            throw new IllegalArgumentException("Rol ismi boş olamazé");
        }
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Kullanıcı bulunadı!");

        }
        Role role = new Role();
        role.setName(name);
        role.setCreatedBy(userId);
        return RoleDto.convertToRoleDto(roleRepository.save(role));
    }
}
