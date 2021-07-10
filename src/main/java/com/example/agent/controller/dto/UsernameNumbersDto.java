package com.example.agent.controller.dto;

import java.io.Serializable;

public class UsernameNumbersDto implements Serializable {
    private String username;
    private Integer quantity;

    public UsernameNumbersDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
