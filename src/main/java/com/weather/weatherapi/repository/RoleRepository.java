package com.weather.weatherapi.repository;

import com.weather.weatherapi.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    Optional<Role> findById(Long id);

    boolean existsByName(String name);

    Page<Role> findAll(Pageable pageable);

    Long deleteRoleById(Long id);
}
