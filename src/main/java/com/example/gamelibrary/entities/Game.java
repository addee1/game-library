package com.example.gamelibrary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String platform;

    @NotBlank
    private String developer;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String imageUrl;

    @Size(max = 1000)
    @Lob
    private String description;
    private boolean featured;
    private boolean favorite;
    @ElementCollection
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre")
    private Set<String> genres = new HashSet<>();
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

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


    // reviews
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
