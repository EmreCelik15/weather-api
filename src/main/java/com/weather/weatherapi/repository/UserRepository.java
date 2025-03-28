package com.weather.weatherapi.repository;

import com.weather.weatherapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);

    Optional<User> findById(Long id);

    User deleteUserById(Long id);

    boolean existsByUsername(String name);
}
