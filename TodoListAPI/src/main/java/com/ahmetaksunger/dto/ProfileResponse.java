package com.ahmetaksunger.dto;

import com.ahmetaksunger.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponse {

    private String username;

    private Set<Role> authorities;

}
