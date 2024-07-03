package by.davlar.spring.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class BirthdateValidator implements ConstraintValidator<Birthdate, LocalDate> {

    private final long MIN_ALLOWED_AGE = 18L;
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return ChronoUnit.YEARS.between(value, LocalDate.now()) >= MIN_ALLOWED_AGE;
    }
}
