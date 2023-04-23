package com.unkownkoder.utils.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("No such a user with the given username");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
