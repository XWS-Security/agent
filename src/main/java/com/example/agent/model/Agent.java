package com.example.agent.model;

import com.example.agent.enums.Gender;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@DiscriminatorValue("AGENT_USER")
public class Agent extends User {
    private transient final String administrationRole = "ROLE_AGENT_USER";

    @Column(name = "mail_activation_code", length = 64)
    private String activationCode;

    @Column(name = "registration_sent_date")
    private Timestamp registrationSentDate;

    @Column(name = "geneder")
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Product> products;

    @Override
    public String getAdministrationRole() {
        return administrationRole;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setRegistrationSentDate(Timestamp registrationSentDate) {
        this.registrationSentDate = registrationSentDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Timestamp getRegistrationSentDate() {
        return registrationSentDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

