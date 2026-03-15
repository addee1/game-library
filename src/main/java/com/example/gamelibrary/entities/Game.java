package com.example.gamelibrary.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String platform;
    private String developer;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private boolean featured;
    private boolean favorite;
    @ElementCollection
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre")
    private Set<String> genres = new HashSet<>();
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Game() {
    }

    public Game(String title, Set<String> genres, String platform, String developer, LocalDate releaseDate, BigDecimal price, String imageUrl, String description, boolean featured, boolean favorite) {
        this.title = title;
        this.genres = genres;
        this.platform = platform;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.featured = featured;
        this.favorite = favorite;
    }

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

    public String getDescription() {
        return description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }


    // Setters
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
