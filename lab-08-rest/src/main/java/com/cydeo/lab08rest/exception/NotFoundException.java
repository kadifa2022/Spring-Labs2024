package com.cydeo.lab08rest.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { // any message will be passed
        super(message);
    }
}
