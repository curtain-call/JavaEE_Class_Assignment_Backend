package com.javaeeAssignment.ai_coach_backend.dto;

public class UserRegisterDto {
    private String username;
    private String password;
    private String email;  // 可选，取决于您的应用需求

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
