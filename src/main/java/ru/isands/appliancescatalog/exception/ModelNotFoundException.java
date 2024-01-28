package ru.isands.appliancescatalog.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}