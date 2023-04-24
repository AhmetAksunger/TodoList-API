package com.unkownkoder.utils.exceptions;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException() {

        super("Invalid name");

    }

    public InvalidNameException(String message) {
        super(message);
    }
}
