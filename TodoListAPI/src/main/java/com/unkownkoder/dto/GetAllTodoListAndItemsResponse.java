package com.unkownkoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTodoListAndItemsResponse {

    String name;

    String description;

    Boolean completed;

    String todoListName;

    private int todoListId;

    String username;

}
