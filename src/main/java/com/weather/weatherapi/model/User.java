package com.weather.weatherapi.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(name = "password_no_hash")
    private String passwordNoHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @Column(name = "created_date", nullable = true)
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "last_login_date", nullable = true)
    private LocalDateTime lastLoginDate = LocalDateTime.now();
    @Column(name = "enabled")
    private Boolean enabled = true; // Hesap aktif mi?
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired = true; // Hesap süresi dolmuş mu?
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = true; // Hesap kilitli mi?
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired = true; // Şifre süresi dolmuş mu?

    // JPA için boş constructor
    public User() {
    }

    public User(Long id, String username, String password, String passwordNoHash, Set<Role> roles, LocalDateTime createdDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordNoHash = passwordNoHash;
        this.roles = roles;
        this.createdDate = createdDate;
    }

    // Getter ve Setter metodları
    // (IDE ile otomatik oluşturabilirsiniz)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getPasswordNoHash() {
        return passwordNoHash;
    }

    public void setPasswordNoHash(String passwordNoHash) {
        this.passwordNoHash = passwordNoHash;
    }
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    // --------------------------
    // UserDetails Metodları (Override)
    // --------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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
