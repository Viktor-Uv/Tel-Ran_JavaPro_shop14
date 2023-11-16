package com.example.shop14.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // будет применяться к полю
@Retention(RetentionPolicy.RUNTIME) // будет использоваться в рантайме
@Constraint(validatedBy = BetweenValidator.class) // логика валидации
@Documented
public @interface Between {
    String message() default "{Between.validation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    double from() default 0.0;
    double to();
}
