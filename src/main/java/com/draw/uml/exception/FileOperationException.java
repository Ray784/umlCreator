package com.draw.uml.exception;

/**
 * This exception is thrown when an error occurs during file operations.
 */
public class FileOperationException extends Exception {
    /**
     * Constructs a new FileOperationException with the specified detail message.
     *
     * @param message the detail message
     */
    public FileOperationException(String message) {
        super(message);
    }
}
