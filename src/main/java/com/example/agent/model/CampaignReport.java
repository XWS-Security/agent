package com.example.agent.model;

public class CampaignReport {
    private int id;
    private int likes;
    private int dislikes;
    private int comments;
    private double revenue;

    public CampaignReport() {
    }

    public CampaignReport(int id, int likes, int dislikes, int comments, double revenue) {
        this.id = id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
        this.revenue = revenue;
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
}
