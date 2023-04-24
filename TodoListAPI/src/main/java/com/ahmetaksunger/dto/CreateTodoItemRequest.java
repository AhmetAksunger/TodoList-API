package com.ahmetaksunger.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoItemRequest {

    @NotNull(message = "TodoItem name cannot be null")
    @NotBlank(message = "TodoItem name cannot be blank")
    private String name;

    private String description;

    private boolean completed;

    @Min(value = 1, message = "TodoList id must be greater than 1")
    private int todoListId;
}
