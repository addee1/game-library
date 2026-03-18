package com.example.gamelibrary.specifications;

import com.example.gamelibrary.entities.Game;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GameSpecification {
    public static Specification<Game> titleOrDeveloperContains(String search) {

        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return null;
            }

            String like = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("title")), like),
                    cb.like(cb.lower(root.get("developer")), like)
            );
        };
    }

    public static Specification<Game> hasGenre(String genre) {
        return (root, query, cb) -> {
            if (genre == null || genre.isBlank()) {
                return null;
            }

            if (query != null) {
                query.distinct(true);
            }

            Join<Game, String> genresJoin = root.join("genres");
            return cb.equal(genresJoin, genre);
        };
    }

    public static Specification<Game> hasPlatform(String platform) {
        return (root, query, cb) -> {
            if (platform == null || platform.isBlank()) {
                return null;
            }

            return cb.equal(cb.lower(root.get("platform")), platform.toLowerCase());
        };
    }

    public static Specification<Game> isFeatured(Boolean featured) {
        return (root, query, cb) -> {
            if (featured == null) {
                return null;
            }

            return cb.equal(root.get("featured"), featured);
        };
    }

    public static Specification<Game> isFavorite(Boolean favorite) {
        return (root, query, cb) -> {
            if (favorite == null) {
                return null;
            }

            return cb.equal(root.get("favorite"), favorite);
        };
    }
}
