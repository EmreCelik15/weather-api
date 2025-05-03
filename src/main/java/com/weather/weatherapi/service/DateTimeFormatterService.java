package com.weather.weatherapi.service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatterService {
    public static DateTimeFormatter dateTimeFormatterToDetailTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter dateTimeFormatterToRiseAndSetDate = DateTimeFormatter.ofPattern("hh:mm a",
            Locale.ENGLISH);
}
