package com.example.agent.model;

import com.example.agent.enums.Gender;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

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
}

