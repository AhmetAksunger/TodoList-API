package com.unkownkoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTodoListAndItemsResponse {

    String name;

    String description;

    Boolean completed;

    String TodoListName;

}
