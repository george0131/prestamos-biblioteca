package com.serfinanzas.prestamos.service.exception;

public class LendException extends RuntimeException {

    final String message;
    final int statusCode;

    public LendException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
