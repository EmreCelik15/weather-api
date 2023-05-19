package com.weather.weatherapi.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author EmreÇelik
 * @Date 19.05.2023
 */
@Configuration
@EnableCaching
public class SpringCachingConfig {
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("weathers");
    }

}
