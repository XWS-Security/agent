package com.example.agent.controller.dto;

import java.io.Serializable;

public class LogInDto implements Serializable {
    private String email;
    private String password;

    public LogInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LogInDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
