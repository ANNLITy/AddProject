package com.example.projectadd.exception;

public class NoAccessException extends RuntimeException {
    public NoAccessException(String message) {
        super(message);
    }

    public NoAccessException() {
    }
}