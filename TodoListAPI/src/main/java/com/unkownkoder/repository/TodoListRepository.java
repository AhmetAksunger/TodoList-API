package com.unkownkoder.repository;

import com.unkownkoder.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList,Integer> {

    List<TodoList> findAllByUserUserId(int id);

    boolean existsByNameAndUserUserId(String name, int userId);
}
