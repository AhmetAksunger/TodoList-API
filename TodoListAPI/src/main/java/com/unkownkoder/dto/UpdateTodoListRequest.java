package com.unkownkoder.dto;

import jakarta.persistence.Entity;
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

    @Min(value = 1)
    private int id;

    @NotBlank(message = "Name cannot be null")
    @NotNull(message = "Name cannot be blank")
    private String name;

}
