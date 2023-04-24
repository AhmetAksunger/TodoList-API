package com.ahmetaksunger.dto;

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
