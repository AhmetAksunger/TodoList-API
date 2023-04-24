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
public class UpdateTodoListRequest {

    @Min(value = 1, message = "Id should be greater than or equal to 1")
    private int id;

    @NotBlank(message = "Name cannot be null")
    @NotNull(message = "Name cannot be blank")
    private String name;

}
