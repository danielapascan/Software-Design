package ro.ps.lab3.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * Validator implementation for the OddPageSize annotation.
 * Ensures that the provided page size is an odd number.
 */
public class OddPageSizeValidator implements ConstraintValidator<OddPageSize, Integer> {
    /**
     * Validates whether the provided value is an odd number.
     *
     * @param value   The value to be validated.
     * @param context The validation context.
     * @return True if the value is an odd number, false otherwise.
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value % 2 == 1;
    }

}
