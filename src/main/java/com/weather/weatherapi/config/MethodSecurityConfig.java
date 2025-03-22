package com.weather.weatherapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize ve @PostAuthorize'u aktif eder
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
