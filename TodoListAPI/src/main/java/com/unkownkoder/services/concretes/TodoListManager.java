package com.unkownkoder.services.concretes;

import com.unkownkoder.dto.CreateTodoListRequest;
import com.unkownkoder.dto.UpdateTodoListRequest;
import com.unkownkoder.entity.TodoList;
import com.unkownkoder.entity.User;
import com.unkownkoder.repository.TodoItemRepository;
import com.unkownkoder.repository.TodoListRepository;
import com.unkownkoder.repository.UserRepository;
import com.unkownkoder.services.UserService;
import com.unkownkoder.services.abstracts.TodoListService;
import com.unkownkoder.utils.mappers.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TodoListManager implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void create(CreateTodoListRequest createTodoListRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();

        TodoList todoList = modelMapperService.forRequest().map(createTodoListRequest,TodoList.class);

        todoList.setUser(user);

        todoListRepository.save(todoList);

    }

    @Override
    public void delete(int id) {

        todoListRepository.deleteById(id);

    }

    @Override
    public void update(UpdateTodoListRequest updateTodoListRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        boolean isValidId = false;

        for (TodoList todoList:user.getTodoLists()) {

            if(todoList.getId() == updateTodoListRequest.getId()){
                isValidId = true;
                break;
            }

        }

        if(!isValidId){
            throw new RuntimeException("Given todolist id does not belong to you.");
        }

        else{

            TodoList todoList = modelMapperService.forRequest().map(updateTodoListRequest,TodoList.class);

            todoList.setUser(user);

            todoListRepository.save(todoList);


        }

    }
}
