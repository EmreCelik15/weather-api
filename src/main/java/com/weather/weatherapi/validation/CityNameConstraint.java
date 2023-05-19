package com.weather.weatherapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CityNameValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/**
 * @author EmreÇelik
 * @Date 10.05.2023
 */
public @interface CityNameConstraint {
    String message() default "Invalid City Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
