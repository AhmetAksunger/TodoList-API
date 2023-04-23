package com.unkownkoder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    private boolean accountNonLocked;


}
