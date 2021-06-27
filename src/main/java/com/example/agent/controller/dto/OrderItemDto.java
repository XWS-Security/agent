package com.example.agent.controller.dto;

import java.io.Serializable;

public class OrderItemDto implements Serializable {
    private long id;
    private int amount;
    private int price;

    public OrderItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
