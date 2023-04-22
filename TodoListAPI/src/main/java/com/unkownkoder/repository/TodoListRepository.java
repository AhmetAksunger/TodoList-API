package com.unkownkoder.repository;

import com.unkownkoder.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList,Integer> {


}
