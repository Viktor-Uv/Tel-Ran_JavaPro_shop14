package com.example.shop14.errors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// регистрирует методы класса как обработчики исключений для всего приложения
@RestControllerAdvice
public class ShopExceptionHandler extends ResponseEntityExceptionHandler {

    // метод будет являться обработчиком исключений для ConstraintViolationException
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(
                violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.ok(errors);
    }
}
