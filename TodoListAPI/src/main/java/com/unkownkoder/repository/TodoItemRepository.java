package com.unkownkoder.repository;

import com.unkownkoder.entity.TodoItem;
import com.unkownkoder.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem,Integer> {

    List<TodoItem> findAllByTodoListIn(List<TodoList> todoLists);


}
