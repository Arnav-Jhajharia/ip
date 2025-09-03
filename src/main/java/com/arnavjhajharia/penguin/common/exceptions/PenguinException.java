package com.arnavjhajharia.penguin.common.exceptions;

/**
 * Base exception type for all custom exceptions in the Penguin application.
 * <p>
 * Extends {@link Exception} to allow specific, meaningful error handling
 * within the application while preserving standard Java exception behavior.
 * All application-specific exceptions should subclass {@code PenguinException}.
 */
public class PenguinException extends Exception {

    /**
     * Constructs a new {@code PenguinException} with the specified detail message.
     *
     * @param message the detail message describing the cause of the exception
     */
    public PenguinException(String message) {
        super(message);
    }
}
