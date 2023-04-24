package com.ahmetaksunger.services.abstracts;

import com.ahmetaksunger.dto.CreateTodoListRequest;
import com.ahmetaksunger.dto.GetAllUserTodoListsResponse;
import com.ahmetaksunger.dto.UpdateTodoListRequest;

import java.util.List;

public interface TodoListService {

    void create(CreateTodoListRequest createTodoListRequest);

    void delete(int id);


    void update(UpdateTodoListRequest updateTodoListRequest);

    List<GetAllUserTodoListsResponse> getAllUserTodoLists();

}
