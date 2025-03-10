package com.weather.weatherapi.authentication;

import com.weather.weatherapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Burada veritabanından kullanıcıyı arayarak bilgilerini alabilirsiniz.
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
//        if ("user".equals(username)) {
//            return User.builder()
//                    .username("user")
//                    .password(passwordEncoder.encode("user"))// {noop} parola şifrelemesiz demektir
//                    .roles("USER")
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
//        }
    }
}

