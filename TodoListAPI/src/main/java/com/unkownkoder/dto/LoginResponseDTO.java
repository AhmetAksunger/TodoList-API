package com.unkownkoder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unkownkoder.entity.User;

public class LoginResponseDTO {
    private String jwt;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}
