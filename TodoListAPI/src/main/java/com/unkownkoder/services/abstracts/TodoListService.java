package com.unkownkoder.services.abstracts;

import com.unkownkoder.dto.CreateTodoListRequest;
import com.unkownkoder.dto.GetAllUserTodoListsResponse;
import com.unkownkoder.dto.UpdateTodoListRequest;

import java.util.List;

public interface TodoListService {

    void create(CreateTodoListRequest createTodoListRequest);

    void delete(int id);


    void update(UpdateTodoListRequest updateTodoListRequest);

    List<GetAllUserTodoListsResponse> getAllUserTodoLists();

}
