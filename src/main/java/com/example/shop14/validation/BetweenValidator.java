package com.example.shop14.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BetweenValidator implements ConstraintValidator<Between, BigDecimal> {
    private BigDecimal from = BigDecimal.ZERO;
    private BigDecimal to = BigDecimal.ZERO;

    @Override
    public void initialize(Between c) {
        // параметры из аннотации Between
        from = BigDecimal.valueOf(c.from());
        to = BigDecimal.valueOf(c.to());
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext c) {
        return value.compareTo(from) >= 0 && value.compareTo(to) <= 0;
    }
}
