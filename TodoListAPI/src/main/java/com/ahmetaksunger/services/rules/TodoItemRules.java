package com.ahmetaksunger.services.rules;

import com.ahmetaksunger.repository.TodoItemRepository;
import com.ahmetaksunger.utils.exceptions.InvalidNameException;
import com.ahmetaksunger.utils.exceptions.TodoItemNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TodoItemRules {

    @Autowired
    private TodoItemRepository  todoItemRepository;

    public void isTodoItemNameValid(String name){
        if(name.length() < 3) {
            throw new InvalidNameException("Name must be at least 3 characters long");
        }
        else if(name.length() > 50){
            throw new InvalidNameException("Name cannot be longer than 50 characters");
        }
    }

    public void isTodoDescValid(String description){
        if(description.length() > 500){
            throw new InvalidNameException("Name cannot be longer than 500 characters");
        }
    }

    public void checkIfTodoItemNameExistsWithTodoListId(String name, int todoListId){

        if(todoItemRepository.existsByNameAndTodoListId(name, todoListId)){
            throw new TodoItemNotFoundException("TodoItem name already exists for the todo list with id " + todoListId);
        }
    }

}
