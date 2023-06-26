package com.example.projectadd.exception;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException(String message) {
        super(message);
    }
    public AdNotFoundException() {
    }
}

