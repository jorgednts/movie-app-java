package com.example.movies_app_java.domain.exception;

public class GenericErrorException extends RuntimeException {
    public GenericErrorException() {
        super("Generic Error Exception occurred");
    }
}
