package com.kamadhenu.signalsusermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h1>File loader exception</h1>
 * <p>
 * This class provides the logic to throw the file load exceptions
 *
 * @author Kamadhenu
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileLoaderException extends RuntimeException {

    /**
     * File loader exception
     *
     * @param message the message for the error
     */
    public FileLoaderException(String message) {
        super(message);
    }

    /**
     * File loader exception
     *
     * @param message the message for the error
     * @param cause   the cause of the error
     */
    public FileLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
