package com.javaeeAssignment.ai_coach_backend.dto;

public class UserRegisterDto {
    private String account;
    private String password;

    public String getUsername() {
        return account;
    }

    public void setUsername(String username) {
        this.account = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
