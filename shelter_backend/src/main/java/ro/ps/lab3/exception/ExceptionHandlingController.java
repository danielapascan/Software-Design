package ro.ps.lab3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Objects;

/**
 * Controller advice class for handling exceptions globally.
 */
@RestControllerAdvice
public class ExceptionHandlingController {
    /**
     * Handles NotFoundException and returns an appropriate HTTP response.
     *
     * @param exception The NotFoundException instance.
     * @return An ExceptionBody object containing the exception message.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionBody handleNotFoundException(NotFoundException exception) {
        return new ExceptionBody(exception.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException and returns an appropriate HTTP response.
     *
     * @param exception The MethodArgumentNotValidException instance.
     * @return An ExceptionBody object containing the exception message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ExceptionBody handleInvalidArgument(MethodArgumentNotValidException exception) {
        return new ExceptionBody(getMessageFromInvalidArguments(exception));
    }

    /**
     * Handles generic Exception and returns an appropriate HTTP response.
     *
     * @param exception The Exception instance.
     * @return An ExceptionBody object containing the exception message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionBody handleException(Exception exception) {
        return new ExceptionBody("EXCEPTION: " + exception.getMessage());
    }

    /**
     * Extracts error messages from MethodArgumentNotValidException.
     *
     * @param exception The MethodArgumentNotValidException instance.
     * @return A string containing error messages.
     */
    private String getMessageFromInvalidArguments(MethodArgumentNotValidException exception) {
        return Arrays.stream(Objects.requireNonNull(exception.getDetailMessageArguments()))
                .filter(message -> !((String) message).isEmpty())
                .toList()
                .toString();
    }
}
