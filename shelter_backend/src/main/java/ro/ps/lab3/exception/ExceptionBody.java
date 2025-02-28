package ro.ps.lab3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class representing the body of an exception response.
 */
@Getter
@RequiredArgsConstructor
public class ExceptionBody {
    private final String message;
    private final LocalDateTime timestamp;

    /**
     * Constructs an ExceptionBody object with the given message and the current timestamp.
     *
     * @param message The error message.
     */
    public ExceptionBody(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
