package com.example.client.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private List<String> picture; // Kép URL-je

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
