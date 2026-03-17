package com.example.gamelibrary.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class CreateGameDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotEmpty(message = "At least one genre must be selected")
    private Set<String> genres;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotBlank(message = "Developer is required")
    private String developer;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be 0 or greater")
    private BigDecimal price;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @Size(max = 1000, message = "Description is too long")
    private String description;

    private boolean featured;



    // Getters
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
    public BigDecimal getPrice() { return price; }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getDescription() {
        return description;
    }
    public boolean getFeatured() { return featured; }



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
    public void setFeatured(boolean featured) { this.featured = featured; }
}
