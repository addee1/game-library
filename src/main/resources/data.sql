-- DEV ONLY (H2 in-memory reset)
DELETE FROM game_genres;
DELETE FROM game;

INSERT INTO game (
    title,
    platform,
    developer,
    release_date,
    price,
    image_url,
    description,
    featured,
    favorite,
    created_at
)
VALUES
('The Witcher 3', 'PC', 'CD Projekt', '2015-05-19', 29.99, 'https://images.igdb.com/igdb/image/upload/t_cover_big/coaxt6.jpg', 'RPG game', true, false, CURRENT_TIMESTAMP),
('Cyberpunk 2077', 'Xbox', 'CD Projekt', '2020-12-10', 49.99, 'https://images.igdb.com/igdb/image/upload/t_cover_big/coaxt6.jpg', 'Futuristic RPG', false, false, CURRENT_TIMESTAMP),
('Elden Ring', 'PlayStation', 'FromSoftware', '2022-02-25', 59.99, 'https://images.igdb.com/igdb/image/upload/t_cover_big/coaxt6.jpg', 'Souls game', true, false, CURRENT_TIMESTAMP);

INSERT INTO game_genres (game_id, genre) VALUES
(1, 'RPG'),
(1, 'Adventure'),
(2, 'RPG'),
(3, 'Action'),
(3, 'RPG');