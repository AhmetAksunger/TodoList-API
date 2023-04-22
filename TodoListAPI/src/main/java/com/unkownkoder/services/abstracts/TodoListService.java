package com.unkownkoder.services.abstracts;

import com.unkownkoder.dto.CreateTodoListRequest;

public interface TodoListService {

    void create(CreateTodoListRequest createTodoListRequest);

    void delete(int id);


}
