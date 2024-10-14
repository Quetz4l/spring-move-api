# Getflix

Getflix is a movie management application built using Java, Spring Boot, and Hibernate.

## Features

- Manage movies
- Manage actors
- Manage genres

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed the 23 version of Java.
- You have installed the latest version of Gradle.
- You have a SQLite database installed.

## Installation

1. Clone the repository:
   ```shell
   git clone https://gitea.kood.tech/Quetz4l/kmdb
   cd kmdb
   ```

2. Build the project with Gradle:
   ```shell
   gradlew build
   ```

3. Run the application:
   ```shell
   java -jar build\libs\getflix-1.1.jar
   ```

## Usage

Once the application is running, you can see the entire API path from Swagger UI `http://localhost:8080/swagger-ui/index.html` or
use the following endpoints:

<details><summary> Movies</summary>

* To get all movies: `GET /movies`
* To get a movie by id: `GET /movies/{id}`
* To create a movie: `POST /movies`
* To update a movie by id: `PATCH /movies/{id}`
* To delete a movie by id, (with `force=true` parameter to force deletion): `DELETE /movies/{id}`

#### Filters (can work with combinations)

* Retrieve movies filtered by genre, can work with incomplete title names: `GET /api/movies/search?title={Title name}`
* Retrieve movies filtered by genre: `GET /api/movies?genre={genreId}`
* Retrieve movies filtered by release year: `GET /api/movies?year={releaseYear}`
* Retrieve movies that the actor with the given id has: `GET /api/movies?actor={Actor.id}`

</details>

<details><summary> Actors </summary>

* To get all actors: `GET /actors`
* To get an actor by id: `GET /actors/{id}`
* To create an actor: `POST /actors`
* To update an actor by id: `PATCH /actors/{id}`
* To delete an actor by id, (with `force=true` parameter to force deletion): `DELETE /actors/{id}`

### Filters

* Retrieve all actors starring in a movie: `GET /api/movies/{movieId}/actors`
* Retrieve actors filtered by name, can work with incomplete names:: `GET /api/actors?name={name}`

</details>


<details><summary> Genres </summary>

* To get all genres: `GET /genres`
* To get a genre by id: `GET /genres/{id}`
* To create a genre: `POST /genres`
* To update a genre by id: `PATCH /genres/{id}`
* To delete a genre by id (with `force=true` parameter to force deletion): `DELETE /genres/{id}`

### Filters

* Retrieve all genres starring in a movie: `GET /api/movies/{movieId}/genres`
* Retrieve genres filtered by name, can work with incomplete names: `GET /api/genres?name={name}`

</details>

## Extra features

* Simple search functionality for movies by title is implemented.
* Pagination is implemented for GET requests that return multiple entities.
* Additional technologies, security enhancements, and features beyond basic requirements have been implemented.
* Case-insensitive and partial match search for movie titles is implemented.
* Proper error handling and input validation for invalid pagination parameters is implemented.
* Basic tests for RestFul API
* Swagger

## Contributing

Contributions are always welcome! Please feel free to open an issue or submit a pull request.

## License

Distributed under the MIT License.
