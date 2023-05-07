package com.weather.weatherapi.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(JsonProcessingException jsonProcessingException){
        String error="message: "+jsonProcessingException.getMessage()+"/n"+
                "originalMessage: " +jsonProcessingException.getOriginalMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
