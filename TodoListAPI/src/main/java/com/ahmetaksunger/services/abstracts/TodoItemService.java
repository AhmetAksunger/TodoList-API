package com.ahmetaksunger.services.abstracts;

import com.ahmetaksunger.dto.CreateTodoItemRequest;
import com.ahmetaksunger.dto.GetAllTodoListAndItemsResponse;
import com.ahmetaksunger.dto.GetUserTodoListAndItemsResponse;
import com.ahmetaksunger.dto.UpdateTodoItemRequest;

import java.util.List;

public interface TodoItemService {

    void create(CreateTodoItemRequest createTodoItemRequest);

    void delete(int id);

    void update(UpdateTodoItemRequest updateTodoItemRequest);

    List<GetAllTodoListAndItemsResponse> getAllTodoListAndItems();

    List<GetUserTodoListAndItemsResponse> getUserTodoListAndItems();



}
