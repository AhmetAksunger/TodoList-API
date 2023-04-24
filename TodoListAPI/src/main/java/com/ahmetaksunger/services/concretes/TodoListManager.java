package com.ahmetaksunger.services.concretes;

import com.ahmetaksunger.dto.CreateTodoListRequest;
import com.ahmetaksunger.dto.GetAllUserTodoListsResponse;
import com.ahmetaksunger.dto.UpdateTodoListRequest;
import com.ahmetaksunger.entity.TodoList;
import com.ahmetaksunger.entity.User;
import com.ahmetaksunger.repository.TodoItemRepository;
import com.ahmetaksunger.repository.TodoListRepository;
import com.ahmetaksunger.repository.UserRepository;
import com.ahmetaksunger.services.abstracts.TodoListService;
import com.ahmetaksunger.services.rules.TodoListRules;
import com.ahmetaksunger.utils.exceptions.TodoListNotFoundException;
import com.ahmetaksunger.utils.mappers.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private TodoListRules rules;
    @Override
    public void create(CreateTodoListRequest createTodoListRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();

        rules.isTodoListNameValid(createTodoListRequest.getName());
        rules.checkIfTodoListNameWithUserIdExists(createTodoListRequest.getName(),user.getUserId());

        TodoList todoList = modelMapperService.forRequest().map(createTodoListRequest,TodoList.class);

        todoList.setUser(user);

        todoListRepository.save(todoList);

    }

    @Override
    public void delete(int id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        TodoList todoList = todoListRepository.findById(id).orElseThrow(() -> new TodoListNotFoundException("No such a todo list with the given id"));

        if(!todoList.getUser().equals(user)){
            throw new TodoListNotFoundException();
        }

        todoListRepository.deleteById(id);

    }

    @Override
    public void update(UpdateTodoListRequest updateTodoListRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();


        rules.isTodoListNameValid(updateTodoListRequest.getName());
        rules.checkIfTodoListNameWithUserIdExists(updateTodoListRequest.getName(),user.getUserId());

        TodoList todoList = todoListRepository.findById(updateTodoListRequest.getId()).orElseThrow(() -> new TodoListNotFoundException("No such a todo list with the given id"));

        if(!todoList.getUser().equals(user)){
            throw new TodoListNotFoundException();
        }

        todoList = modelMapperService.forRequest().map(updateTodoListRequest,TodoList.class);

        todoList.setUser(user);

        todoListRepository.save(todoList);


    }

    @Override
    public List<GetAllUserTodoListsResponse> getAllUserTodoLists() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        List<TodoList> todoLists = todoListRepository.findAllByUserUserId((user.getUserId()));

        List<GetAllUserTodoListsResponse> responses = todoLists.stream()
                .map(todoList -> modelMapperService.forResponse().map(todoList, GetAllUserTodoListsResponse.class))
                .collect(Collectors.toList());

        return responses;
    }
}
