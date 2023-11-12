package com.example.client.model;

public class Date {
    private int id;
    private String title;
    private String description = title;
    private int price = 0;
    private int owner = 0;
    private String place = "Anywhere";
    private String crowded = "Medium";
    private String activity = "Usual";
    private String season = "Anytime";
    private String duration = "Half day";
    private String daytime = "Anytime";

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCrowded() {
        return crowded;
    }

    public void setCrowded(String crowded) {
        this.crowded = crowded;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public Date(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Date( String title, String description, int price, String place, String crowded, String activity, String season, String duration, String daytime) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.place = place;
        this.crowded = crowded;
        this.activity = activity;
        this.season = season;
        this.duration = duration;
        this.daytime = daytime;
    }
}
