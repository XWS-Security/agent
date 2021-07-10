package com.example.agent.controller.dto;

import com.example.agent.model.CampaignReport;

import java.io.Serializable;

public class ReportDto implements Serializable {
    private int id;
    private int likes;
    private int dislikes;
    private int comments;
    private double revenue;
    private long campaignId;
    private String date;

    public ReportDto() {
    }

    public ReportDto(CampaignReport campaignReport) {
        this.id = campaignReport.getId();
        this.likes = campaignReport.getLikes();
        this.dislikes = campaignReport.getDislikes();
        this.comments = campaignReport.getComments();
        this.revenue = campaignReport.getRevenue();
        this.campaignId = campaignReport.getCampaignId();
        this.date = campaignReport.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
