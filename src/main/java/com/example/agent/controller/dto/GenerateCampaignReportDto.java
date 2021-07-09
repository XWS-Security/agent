package com.example.agent.controller.dto;

import java.io.Serializable;

public class GenerateCampaignReportDto implements Serializable {
    private String token;
    private String message;

    public GenerateCampaignReportDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
