package com.kamadhenu.signalsusermanagement.exception;

/**
 * <h1>File storage exception</h1>
 * <p>
 * This class provides the logic to throw the file storage exceptions
 *
 * @author Kamadhenu
 */
public class FileStorageException extends RuntimeException {

    /**
     * File storage exception
     *
     * @param message the message for the error
     */
    public FileStorageException(String message) {
        super(message);
    }

    /**
     * File storage exception
     *
     * @param message the message for the error
     * @param cause   the cause for the error
     */
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
