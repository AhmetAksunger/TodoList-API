package com.unkownkoder.utils.exceptions;

public class TodoItemNotFoundException extends RuntimeException {

    public TodoItemNotFoundException(String message) {
        super(message);
    }

    public TodoItemNotFoundException() {
        super("The requested todo item was not found in your todo lists");
    }
}
