package com.ahmetaksunger.utils.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Invalid password");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
