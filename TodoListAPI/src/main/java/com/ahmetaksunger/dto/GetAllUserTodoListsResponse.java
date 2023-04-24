package com.ahmetaksunger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserTodoListsResponse {
    private int id;
    private String name;

}
