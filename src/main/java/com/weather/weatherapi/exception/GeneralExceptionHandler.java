package com.weather.weatherapi.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * @author EmreÇelik
 * @Date 1.05.2023
 */
@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(JsonProcessingException jsonProcessingException) {
        String error = "message: " + jsonProcessingException.getMessage() + "/n" +
                "originalMessage: " + jsonProcessingException.getOriginalMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        List<String> errors = new ArrayList<>();
        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            String errorMessage = constraintViolation.getMessage();
            errors.add(errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    protected ResponseEntity<?> handleRequestNotPermittedException(RequestNotPermitted requestNotPermitted) {
        return new ResponseEntity<>(requestNotPermitted.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(InvocationTargetException.class)
    protected ResponseEntity<?> handleInvocationTargetException(InvocationTargetException invocationTargetException) {
        return new ResponseEntity<>(invocationTargetException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException) {

        if (runtimeException.getMessage().equals("Bad credentials")) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(DateTimeParseException.class)
    protected ResponseEntity<?> handleDateTimeParseException(DateTimeParseException dateTimeParseException) {
        return new ResponseEntity<>(dateTimeParseException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
