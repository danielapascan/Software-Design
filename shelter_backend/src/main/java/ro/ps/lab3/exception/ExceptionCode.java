package ro.ps.lab3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing different exception codes along with their corresponding error messages.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    ERR001_ANIMAL_NOT_FOUND("Animal with ID %s not found"),
    ERR002_EMPLOYEE_NOT_FOUND("Employee with ID %s not found"),
    ERR003_CUSTOMER_NOT_FOUND("Customer with ID %s not found"),
    ERR004_VOLUNTEER_NOT_FOUND("Volunteer with ID %s not found"),
    ERR005_ANIMAL_NEEDS_NOT_FOUND("Animal Needs with ID %s not found"),
    ERR006_ADOPTION_NOT_FOUND("Adoption with ID %s not found"),
    ERR007_DONATION_NOT_FOUND("Donation with ID %s not found"),
    ERR008_EMAIL_NOT_FOUND("Email not found!"),
    ERR099_INVALID_CREDENTIALS("Invalid credentials."),
    ERR009_INVALID_ADOPTION("Invalid adoption.");
    private final String message;
}
