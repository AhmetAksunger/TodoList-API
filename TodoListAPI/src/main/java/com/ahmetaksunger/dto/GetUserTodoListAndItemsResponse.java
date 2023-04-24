package com.ahmetaksunger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTodoListAndItemsResponse {

    private String TodoListName;

    private int TodoListId;

    private String name;

    private String description;

    private Boolean completed;



}
