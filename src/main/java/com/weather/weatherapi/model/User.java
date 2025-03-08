package com.weather.weatherapi.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Kullanıcı rolü (örneğin: USER, ADMIN)
    private LocalDateTime createdDate=LocalDateTime.now();

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private boolean enabled = true; // Hesap aktif mi?
    private boolean accountNonExpired = true; // Hesap süresi dolmuş mu?
    private boolean accountNonLocked = true; // Hesap kilitli mi?
    private boolean credentialsNonExpired = true; // Şifre süresi dolmuş mu?

    // JPA için boş constructor
    public User() {}

    // Getter ve Setter metodları
    // (IDE ile otomatik oluşturabilirsiniz)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public void setUsername(String email) { this.username = email; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    // --------------------------
    // UserDetails Metodları (Override)
    // --------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password; // Şifre (hash'lenmiş olmalı)
    }

    @Override
    public String getUsername() {
        return username; // Spring Security için "username" = email
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
