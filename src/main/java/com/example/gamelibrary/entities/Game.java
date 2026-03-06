package com.example.gamelibrary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String platform;
    private String developer;
    private LocalDate releaseDate;
    private String imageUrl;
    private String description;
    private boolean featured;
    private boolean favorite;

    public Game() {
    }

    public Game(String title, String genre, String platform, String developer, LocalDate releaseDate, String imageUrl, String description, boolean featured, boolean favorite) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.description = description;
        this.featured = featured;
        this.favorite = favorite;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
