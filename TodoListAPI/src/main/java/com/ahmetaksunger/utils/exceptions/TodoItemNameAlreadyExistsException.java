package com.ahmetaksunger.utils.exceptions;

public class TodoItemNameAlreadyExistsException extends RuntimeException{

    public TodoItemNameAlreadyExistsException() {
        super("TodoItem name already exists");
    }

    public TodoItemNameAlreadyExistsException(String message) {
        super(message);
    }
}
