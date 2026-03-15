package com.example.gamelibrary.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class GameDTO {

    private Long id;
    private String title;
    private Set<String> genres;
    private String platform;
    private String developer;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private boolean featured;
    private boolean favorite;
    private LocalDateTime createdAt;



    // Getters
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Set<String> getGenres() { return genres; }
    public String getPlatform() {
        return platform;
    }
    public String getDeveloper() {
        return developer;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public BigDecimal getPrice() {return price;}
    public String getImageUrl() {
        return imageUrl;
    }
    public String getDescription() { return description; }
    public boolean isFeatured() { return featured; }
    public boolean isFavorite() { return favorite; }
    public LocalDateTime getCreatedAt() { return createdAt; }



    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenres(Set<String> genres) { this.genres = genres; }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setDescription(String description) { this.description = description; }
    public void setFeatured(boolean featured) { this.featured = featured; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
