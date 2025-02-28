package ro.ps.lab3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Exception class for representing a not found exception.
 */
@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;

}
