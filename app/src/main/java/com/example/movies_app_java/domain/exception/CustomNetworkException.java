package com.example.movies_app_java.domain.exception;

public class CustomNetworkException extends RuntimeException {
    public CustomNetworkException() {
        super("Network Exception occurred");
    }
}
