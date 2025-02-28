package ro.ps.lab3.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
/**
 * Custom validation annotation to ensure that the page size is an odd number.
 */
@Documented
@Constraint(validatedBy = OddPageSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OddPageSize {
    /**
     * Message to be returned when validation fails.
     *
     * @return The error message.
     */
    String message() default "Page size must be an odd number";
    /**
     * Groups to which this constraint belongs.
     *
     * @return An array of group classes.
     */
    Class<?>[] groups() default {};
    /**
     * Payload for the validation.
     *
     * @return An array of payload classes.
     */
    Class<? extends Payload>[] payload() default {};

}
