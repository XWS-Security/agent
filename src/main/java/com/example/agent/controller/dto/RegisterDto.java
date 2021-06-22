package com.example.agent.controller.dto;

import com.example.agent.enums.Gender;
import com.example.agent.model.Agent;

public class RegisterDto {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String repeatedPassword;
    private Gender gender;

    public RegisterDto() {

    }

    public RegisterDto(String name, String surname, String email, String password, String repeatedPassword, String username,
                       Gender gender) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.gender = gender;
    }

    public RegisterDto(String name, String surname, String email, String username,
                       Gender gender) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
    }

    public static UserDto convertUserToDto(Agent user) {
        return new UserDto(user.getName(), user.getSurname(), user.getEmail(), user.getUsername());
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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
}
