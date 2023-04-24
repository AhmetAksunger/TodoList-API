package com.ahmetaksunger.utils.exceptions;

public class TodoListNameAlreadyExistsException extends RuntimeException {

    public TodoListNameAlreadyExistsException() {

        super("TodoList name already exists");

    }

    public TodoListNameAlreadyExistsException(String message) {
        super(message);
    }
}
