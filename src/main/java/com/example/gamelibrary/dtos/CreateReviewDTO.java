package com.example.gamelibrary.dtos;
import jakarta.validation.constraints.*;

public class CreateReviewDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name is too long")
    private String username;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @Size(max = 1000, message = "Comment is too long")
    private String comment;

    public String getUsername() {
        return username;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
