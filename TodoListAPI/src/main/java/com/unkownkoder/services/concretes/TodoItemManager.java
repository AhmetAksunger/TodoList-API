package com.unkownkoder.services.concretes;

import com.unkownkoder.dto.CreateTodoItemRequest;
import com.unkownkoder.dto.GetAllTodoListAndItemsResponse;
import com.unkownkoder.dto.GetUserTodoListAndItemsResponse;
import com.unkownkoder.dto.UpdateTodoItemRequest;
import com.unkownkoder.entity.TodoItem;
import com.unkownkoder.entity.TodoList;
import com.unkownkoder.entity.User;
import com.unkownkoder.repository.TodoItemRepository;
import com.unkownkoder.repository.UserRepository;
import com.unkownkoder.services.abstracts.TodoItemService;
import com.unkownkoder.services.rules.TodoItemRules;
import com.unkownkoder.utils.exceptions.TodoItemNotFoundException;
import com.unkownkoder.utils.exceptions.TodoListNotFoundException;
import com.unkownkoder.utils.mappers.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoItemManager implements TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private TodoItemRules rules;
    @Override
    public void create(CreateTodoItemRequest createTodoItemRequest) {

        rules.isTodoItemNameValid(createTodoItemRequest.getName());
        rules.isTodoDescValid(createTodoItemRequest.getDescription());
        rules.checkIfTodoItemNameExistsWithTodoListId(createTodoItemRequest.getName(),createTodoItemRequest.getTodoListId());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();

        boolean todoListExists = false;

        for (TodoList todoList: user.getTodoLists()) {
            if(todoList.getId() == createTodoItemRequest.getTodoListId()){
                todoListExists = true;
                break;
            }

        }

        if(!todoListExists){
            throw new TodoListNotFoundException();
        }

        TodoItem todoItem = modelMapperService.forRequest().map(createTodoItemRequest,TodoItem.class);

        todoItemRepository.save(todoItem);

    }

    @Override
    public void delete(int id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow();

        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new TodoItemNotFoundException("No such a todo item with the given id"));

        TodoList todoList = todoItem.getTodoList();

        if(!todoList.getUser().equals(user)){
            throw new TodoListNotFoundException();
        }

        todoItemRepository.deleteById(id);


/*
        boolean isValidId = false;

        for (TodoItem todoItem: todoItemRepository.findAllByTodoListInOrderByTodoListName(user.getTodoLists())) {

            if(todoItem.getId() == id){
                isValidId = true;
                break;

            }

        }

        if(!isValidId){
            throw new TodoListNotFoundException();
        }

        todoItemRepository.deleteById(id);
*/
    }

    @Override
    public void update(UpdateTodoItemRequest updateTodoItemRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        TodoItem todoItem = todoItemRepository.findById(updateTodoItemRequest.getId()).orElseThrow(() -> new TodoItemNotFoundException("No such a todo item with the given id"));
        TodoList todoList = todoItem.getTodoList();

        if(!todoList.getUser().equals(user)){
            throw new TodoItemNotFoundException();
        }

        todoItem = modelMapperService.forRequest().map(updateTodoItemRequest,TodoItem.class);
        todoItem.setTodoList(todoList);


        todoItemRepository.save(todoItem);
    }

    @Override
    public List<GetAllTodoListAndItemsResponse> getAllTodoListAndItems() {

        List<TodoItem> todoItems =  todoItemRepository.findAll();

        List<GetAllTodoListAndItemsResponse> responses = todoItems.stream().map(todoItem->modelMapperService.forResponse().map(todoItem, GetAllTodoListAndItemsResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public List<GetUserTodoListAndItemsResponse> getUserTodoListAndItems() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        List<TodoItem> todoItems = todoItemRepository.findAllByTodoListInOrderByTodoListName(user.getTodoLists());

        List<GetUserTodoListAndItemsResponse> responses = todoItems.stream().map(todoItem->modelMapperService.forResponse().map(todoItem, GetUserTodoListAndItemsResponse.class)).collect(Collectors.toList());


        return responses;
    }
}
