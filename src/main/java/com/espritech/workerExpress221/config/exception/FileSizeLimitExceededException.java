package com.espritech.workerExpress221.config.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class FileSizeLimitExceededException extends RuntimeException {



    private ErrorCodes errorCode;
    private List<String> errors;

    public FileSizeLimitExceededException(String message) {
        super(message);
    }

    public FileSizeLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeLimitExceededException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public FileSizeLimitExceededException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public FileSizeLimitExceededException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

}
