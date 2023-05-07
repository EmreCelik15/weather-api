package com.weather.weatherapi.repository;

import com.weather.weatherapi.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@Repository
public interface WeatherRepository extends JpaRepository<Weather, String> {
    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
