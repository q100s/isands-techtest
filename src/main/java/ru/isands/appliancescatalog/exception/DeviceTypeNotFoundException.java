package ru.isands.appliancescatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeviceTypeNotFoundException extends RuntimeException {
    public DeviceTypeNotFoundException() {
    }

    public DeviceTypeNotFoundException(String message,
                                       Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
