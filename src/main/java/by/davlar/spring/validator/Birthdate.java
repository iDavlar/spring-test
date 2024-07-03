package by.davlar.spring.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BirthdateValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface Birthdate {

    String message() default "You are way too young!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
