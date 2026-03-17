package com.example.gamelibrary.models;

public class GenreCard {
    private String name;
    private String imageUrl;

    public GenreCard(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
