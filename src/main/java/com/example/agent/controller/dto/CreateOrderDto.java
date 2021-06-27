package com.example.agent.controller.dto;

import java.io.Serializable;
import java.util.List;

public class CreateOrderDto implements Serializable {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private AddressDto address;
    private List<CreateOrderItemDto> items;

    public CreateOrderDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<CreateOrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderItemDto> items) {
        this.items = items;
    }
}
