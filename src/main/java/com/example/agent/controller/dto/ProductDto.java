package com.example.agent.controller.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private String picture;
    private String price;
    private String quantity;

    public ProductDto() {

    }

    public ProductDto(Long id, String name, String picture, String price, String quantity) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
