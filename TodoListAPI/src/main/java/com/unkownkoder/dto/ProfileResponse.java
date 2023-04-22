package com.unkownkoder.dto;

import com.unkownkoder.entity.Role;
import com.unkownkoder.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponse {

    private String username;

    private Set<Role> authorities;

}
