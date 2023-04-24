package com.ahmetaksunger.controllers;

import com.ahmetaksunger.dto.GetAllTodoListAndItemsResponse;
import com.ahmetaksunger.services.abstracts.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private TodoItemService todoItemService;
    @GetMapping("/")
    public String helloAdmineController(){
        return "Admin level access";
    }

    @GetMapping("/get/todolistsanditems")
    public ResponseEntity<List<GetAllTodoListAndItemsResponse>> getAllTodoListAndItems(){

        return ResponseEntity.ok(todoItemService.getAllTodoListAndItems());
    }


}
