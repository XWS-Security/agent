package com.example.agent.model;

public class CampaignReport {
    private int id;
    private int likes;
    private int dislikes;
    private int comments;
    private double revenue;
    private long campaignId;
    private String date;
    private String clicks;
    private int views;

    public CampaignReport() {
    }

    public CampaignReport(int id, int likes, int dislikes, int comments, double revenue, long campaignId, String date, String clicks, int views) {
        this.id = id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
        this.revenue = revenue;
        this.campaignId = campaignId;
        this.date = date;
        this.clicks = clicks;
        this.views = views;
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

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "CampaignReport{" +
                "id=" + id +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", comments=" + comments +
                ", revenue=" + revenue +
                ", campaignId=" + campaignId +
                ", date='" + date + '\'' +
                ", clicks='" + clicks + '\'' +
                ", views=" + views +
                '}';
    }
}
