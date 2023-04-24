package com.ahmetaksunger.repository;

import com.ahmetaksunger.entity.TodoItem;
import com.ahmetaksunger.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem,Integer> {

    List<TodoItem> findAllByTodoListInOrderByTodoListName(List<TodoList> todoLists);

    boolean existsByNameAndTodoListId(String name, int todoListId);

}
