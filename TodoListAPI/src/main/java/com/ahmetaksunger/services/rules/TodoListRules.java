package com.ahmetaksunger.services.rules;

import com.ahmetaksunger.repository.TodoListRepository;
import com.ahmetaksunger.utils.exceptions.InvalidNameException;
import com.ahmetaksunger.utils.exceptions.TodoListNameAlreadyExistsException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TodoListRules {

    @Autowired
    private TodoListRepository todoListRepository;

    public void isTodoListNameValid(String name){
        if(name.length() < 3) {
            throw new InvalidNameException("Name must be at least 3 characters long");
        }
        else if(name.length() > 50){
            throw new InvalidNameException("Name cannot be longer than 50 characters");
        }
    }


    public void checkIfTodoListNameWithUserIdExists(String name, int userId){
        if(todoListRepository.existsByNameAndUserUserId(name,userId)){
            throw new TodoListNameAlreadyExistsException("TodoList name already exists for the user with id " + userId);
        }
    }





}
