package com.manish.example.exception;

public class PortNotFreeException extends Exception {
    public PortNotFreeException(String message) {
        super(message);
    }
}
