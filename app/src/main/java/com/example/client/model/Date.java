package com.example.client.model;

public class Date {
    private String title;
    private String description;
    private int price;

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


    public Date(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
