package com.javaeeAssignment.ai_coach_backend.dto;

public class UserDto {
    private Long id;
    private String username;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String emal) {
        this.email = emal;
    }

    public String getEmail() {
        return email;
    }

}