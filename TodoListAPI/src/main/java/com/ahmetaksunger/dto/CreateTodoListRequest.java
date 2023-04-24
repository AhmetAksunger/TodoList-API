package com.ahmetaksunger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoListRequest {

    @NotNull(message = "List name cannot be null")
    @NotBlank(message = "List name cannot be blank")
    private String name;

    /*
    @Min(value = 1, message = "User id must be greater than 1")
    private int userId;
     */
}
