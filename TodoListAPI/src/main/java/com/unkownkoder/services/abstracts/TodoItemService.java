package com.unkownkoder.services.abstracts;

import com.unkownkoder.dto.CreateTodoItemRequest;
import com.unkownkoder.dto.GetAllTodoListAndItemsResponse;
import com.unkownkoder.dto.GetUserTodoListAndItemsResponse;

import java.util.List;

public interface TodoItemService {

    void create(CreateTodoItemRequest createTodoItemRequest);

    void delete(int id);

    List<GetAllTodoListAndItemsResponse> getAllTodoListAndItems();

    List<GetUserTodoListAndItemsResponse> getUserTodoListAndItems();



}
