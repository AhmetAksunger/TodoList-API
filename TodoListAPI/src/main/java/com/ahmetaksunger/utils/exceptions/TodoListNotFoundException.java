package com.ahmetaksunger.utils.exceptions;

public class TodoListNotFoundException extends RuntimeException{

    public TodoListNotFoundException(String message) {
        super(message);
    }

    public TodoListNotFoundException() {
        super("The requested todo list was not found in your account");
    }
}
