package com.ahmetaksunger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String description;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;

}
