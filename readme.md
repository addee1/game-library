# 🎮 Game Library – Spring Boot Web Application

## 📌 Overview

Game Library is a web-based application built with **Spring Boot** that allows users to browse, manage, and review games.

The project demonstrates a modern backend architecture using:

* Spring MVC
* Thymeleaf (server-side rendering)
* Spring Data JPA
* DTO & Mapper pattern
* Filtering with Specifications
* Validation & error handling
* Comprehensive testing

---

## 🚀 Features

### 🎮 Game Management

* View all games
* Create new games
* Update existing games
* Delete games

### 🔍 Filtering & Search (VG requirement)

* Search by title or developer
* Filter by:

    * Genre
    * Platform
    * Featured games
* Sort by price (low → high / high → low)
* Pagination support

### 📄 Game Details

* View detailed information about a game
* See genres, price, platform, developer, etc.

### ⭐ Reviews

* Add reviews with:

    * Username
    * Rating (1–5 stars)
    * Comment (optional)
* View all reviews for a game
* Average rating calculation

> ⚠️ Note: Updating/deleting reviews is intentionally not implemented.

### ❤️ Favorites (Partially implemented)

* Games can be marked as favorite (backend support exists)
* "My Games" page is present but **not fully implemented yet**

---

## 🧭 How to Use

1. Go to the **Home page** to see featured and recently added games
2. Navigate to **Discover Games** to:

    * Search for games
    * Filter by genre/platform
    * Sort by price
3. Click a game to view details and reviews
4. Add a review using the form on the game details page
5. Go to **Settings** to:

    * Add new games
    * Edit existing games
    * Delete games

---

## 🏗️ Architecture

The application follows a layered architecture:

```
Controller → Service → Repository → Database
                ↓
              Mapper
                ↓
               DTO
```

### Layers Explained

* **Controller**

    * Handles HTTP requests
    * Returns Thymeleaf views

* **Service**

    * Contains business logic
    * Uses repositories and mappers

* **Repository**

    * Spring Data JPA
    * Handles database access

* **DTOs**

    * Used for form handling and view rendering
    * Includes validation

* **Mapper**

    * Converts between Entity and DTO

* **Specifications**

    * Dynamic filtering using JPA Criteria API

---

## 🧱 Tech Stack

* Java 17 or higher
* Spring Boot
* Spring MVC
* Spring Data JPA
* Thymeleaf
* H2 Database (in-memory)
* JUnit 5
* Mockito
* MockMvc
* JaCoCo

---

## ⚙️ Requirements

* Java 17+
* Maven (or use the included Maven Wrapper)

---

## 🗄️ Database

* Uses **H2 in-memory database**
* Preloaded with sample data via `data.sql`
* Data resets on every application restart

Example data includes:

* The Witcher 3
* Cyberpunk 2077
* Elden Ring

---

## ⚡ Database Configuration

* The application automatically runs `data.sql` on startup
* This seeds the database with initial data
* Any changes made during runtime are **not persisted**

---

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd game-library
```

### 2. Run the application

```bash
./mvnw spring-boot:run
```

or run via your IDE.

### 3. Open in browser

```
http://localhost:8080
```

### 4. H2 Console (optional)

```
http://localhost:8080/h2-console
```

---

## 📂 Project Structure

```
src/
 ├── controllers/
 ├── services/
 ├── repositories/
 ├── entities/
 ├── dtos/
 ├── mappers/
 ├── specifications/
 ├── exceptions/
 └── configs/

resources/
 ├── templates/ (Thymeleaf views)
 ├── static/
 │   ├── css/
 │   ├── js/
 │   └── images/
 └── data.sql
```

---

## 🎨 Frontend

* Built with Thymeleaf templates

* Modular structure:

    * `layout/`
    * `fragments/`
    * `pages/`

* Custom CSS architecture:

    * base/
    * components/
    * layout/
    * pages/
    * themes (dark/light mode)

* Vanilla JavaScript for:

    * Modals
    * Filtering
    * Theme switching
    * Review interactions

---

## 🧪 Testing

The project includes extensive testing:

### ✅ Unit Tests

* Service layer
* Mapper classes

### ✅ Controller Tests

* MockMvc
* Verifies:

    * HTTP responses
    * Views
    * Model attributes

### 📊 Coverage

* Measured using JaCoCo
* ~76% line coverage

---

## ⚠️ Known Limitations

* ❌ My Games (favorites page) is not fully implemented
* ❌ No authentication (single-user system)
* ❌ Reviews cannot be edited or deleted

---

## 🔮 Future Improvements

* Complete Favorites system (My Games page)
* Add user authentication
* Improve UI/UX interactions
* Add REST API
* Add review editing/deleting
* Improve test coverage further

---

## 👨‍💻 Author

Developed as part of a Spring Boot web development assignment.
