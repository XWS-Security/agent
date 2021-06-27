package com.example.agent.controller.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private String picture;
    private int price;
    private int quantity;

    public ProductDto() {

    }

    public ProductDto(Long id, String name, String picture, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
