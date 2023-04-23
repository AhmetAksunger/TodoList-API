package com.unkownkoder.controllers;

import com.unkownkoder.dto.*;
import com.unkownkoder.services.UserService;
import com.unkownkoder.services.abstracts.TodoItemService;
import com.unkownkoder.services.abstracts.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile() {

        return ResponseEntity.ok(userService.getProfile());

    }

    @PostMapping("/create/todolist")
    public ResponseEntity<String> createTodoList(@Validated @RequestBody CreateTodoListRequest createTodoListRequest){

        todoListService.create(createTodoListRequest);

        return ResponseEntity.ok("Created successfully");
    }

    @PostMapping("/create/todoitem")
    public ResponseEntity<String> createTodoItem(@Validated @RequestBody CreateTodoItemRequest createTodoItemRequest){

        todoItemService.create(createTodoItemRequest);

        return ResponseEntity.ok("Created successfully");
    }

    @DeleteMapping("/delete/todolist/{id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable("id") int id){

        todoListService.delete(id);

        return ResponseEntity.ok("Deleted successfully");
    }


    @DeleteMapping("/delete/todoitem/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable("id") int id){

        todoItemService.delete(id);

        return ResponseEntity.ok("Deleted successfully");
    }


    @PutMapping("/update/todolist")
    public ResponseEntity<UpdateTodoListRequest> updateTodoList(@Validated @RequestBody UpdateTodoListRequest updateTodoListRequest){

        todoListService.update(updateTodoListRequest);

        return ResponseEntity.ok(updateTodoListRequest);
    }

    @PutMapping("/update/todoitem")
    public ResponseEntity<UpdateTodoItemRequest> updateTodoItem(@Validated @RequestBody UpdateTodoItemRequest updateTodoItemRequest){

        todoItemService.update(updateTodoItemRequest);
        return ResponseEntity.ok(updateTodoItemRequest);
    }

    @GetMapping("/get/todolistsanditems")
    public ResponseEntity<List<GetUserTodoListAndItemsResponse>> getTodoListsAndItems(){

        return ResponseEntity.ok(todoItemService.getUserTodoListAndItems());
    }

    @GetMapping("/get/todolists")
    public ResponseEntity<List<GetAllUserTodoListsResponse>> getAllTodoList(){

        return ResponseEntity.ok(todoListService.getAllUserTodoLists());
    }

    @PutMapping("/profile/update")
    public ResponseEntity<UpdateUserRequest> updateUser(@Validated @RequestBody UpdateUserRequest updateUserRequest){

        userService.updateUser(updateUserRequest);

        return ResponseEntity.ok(updateUserRequest);
    }
}
