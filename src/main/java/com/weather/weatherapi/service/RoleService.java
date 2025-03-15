package com.weather.weatherapi.service;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.RoleDto;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public GenericResponse<RoleDto> addRole(String name, Long userId) {
        if (roleRepository.existsByName(name) && name != null && !name.isEmpty()) {
            throw new RuntimeException("Rol Zaten Kayıtlı!");
        }
        if (name == null && name.isEmpty()) {
            throw new IllegalArgumentException("Rol İsmi Boş Olamaz!");
        }
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Kullanıcı bulunamadı!");

        }
        Role role = new Role();
        role.setName(name);
        logger.info("Rol ekleniyor!");
        role.setCreatedBy(userId);
        logger.info("Rol eklendi!");
        return new GenericResponse<>(true, "Rol Eklendi!", RoleDto.convertToRoleDto(roleRepository.save(role)));
    }

    public GenericResponse<Page<RoleDto>> getAllRole(Pageable pageable) {
        logger.info("Roller getirildi!");
        Page<RoleDto> roles=RoleDto.converToAllRoleDto(roleRepository.findAll(pageable));
        logger.info("Roller getirildi!");
        return new GenericResponse<>(true, "Tüm Roller Getirildi!", roles);
    }

    public GenericResponse<RoleDto> getRole(Long id) {
        if (roleRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Role bulunamadı!");
        }
        logger.info("Rol getirildi!");
        return new GenericResponse<>(true, "Rol Getirildi!", RoleDto.convertToRoleDto(roleRepository.findById(id).get()));
    }
}
