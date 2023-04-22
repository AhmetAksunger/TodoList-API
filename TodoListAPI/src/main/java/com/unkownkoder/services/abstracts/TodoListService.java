package com.unkownkoder.services.abstracts;

import com.unkownkoder.dto.CreateTodoListRequest;
import com.unkownkoder.dto.UpdateTodoListRequest;

public interface TodoListService {

    void create(CreateTodoListRequest createTodoListRequest);

    void delete(int id);


    void update(UpdateTodoListRequest updateTodoListRequest);

}
