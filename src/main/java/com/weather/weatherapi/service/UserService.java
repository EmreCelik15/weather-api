package com.weather.weatherapi.service;

import com.weather.weatherapi.common.GenericResponse;
import com.weather.weatherapi.dto.UserDto;
import com.weather.weatherapi.dto.UserRegistrationRequest;
import com.weather.weatherapi.model.Role;
import com.weather.weatherapi.model.User;
import com.weather.weatherapi.repository.RoleRepository;
import com.weather.weatherapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public GenericResponse<UserDto> addUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı'"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Rol Bulunamadı!"));
        if (user.getRoles().contains(role)) {
            throw new RuntimeException("Kullanıcı zaten bu role sahip:" + role);
        }
        user.getRoles().add(role);
        UserDto userDto = UserDto.convertUserToUserDto(userRepository.save(user));
        return new GenericResponse<>(true, "Kullanıcı Eklendi", userDto, HttpStatus.OK);
    }

    public GenericResponse<UserDto> addUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.existsByUsername(userRegistrationRequest.username())) {
            throw new RuntimeException("Kullanıcı Zaten Kayıtlı!");
        }
        if (userRegistrationRequest.username() == null || userRegistrationRequest.username().isEmpty()) {
            throw new IllegalArgumentException("Kullanıcı Adı Boş Olamaz!");
        }
        logger.info("Kullanıcı ekleniyor!");
        User user = new User();
        user.setUsername(userRegistrationRequest.username());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.password()));
        user.setPasswordNoHash(userRegistrationRequest.password());
        UserDto userDto = UserDto.convertUserToUserDto(userRepository.save(user));
        logger.info("Kullanıcı eklendi!");
        return new GenericResponse<>(true, "Kullanıcı Eklendi!", userDto, HttpStatus.OK);
    }

    public GenericResponse<Page<UserDto>> getAllUsers(Pageable pageable) {
        Page<UserDto> users = UserDto.converToAllUserDto(userRepository.findAll(pageable));
        return new GenericResponse<>(true, "Tüm Kullanıcılar Getirildi!", users, HttpStatus.OK);
    }

    public GenericResponse<UserDto> getUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UsernameNotFoundException("Kullanıcı Bulunamadı!");
        }
        UserDto userDto = UserDto.convertUserToUserDto(userRepository.findById(userId).get());
        return new GenericResponse<>(true, "Kullanıcı Getirildi!", userDto, HttpStatus.OK);
    }

    @Transactional
    public GenericResponse<UserDto> deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Kullanıcı Bulunamadı!");
        }
        UserDto userDto = UserDto.convertUserToUserDto(userRepository.findById(userId).get());
        userRepository.deleteUserById(userId);
        return new GenericResponse<>(true, "Kullanıcı Silindi", userDto, HttpStatus.OK);
    }
}
