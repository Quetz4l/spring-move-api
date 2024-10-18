# Getflix

Getflix is a movie management application built using Java, Spring Boot, and Hibernate.

## Features

- Manage movies
- Manage actors
- Manage genres

## Prerequisites

Before you begin, ensure you have met the following requirements:

- 23 Java
- Gradle
- SQLite

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

* `GET /movies` - To get all movies
* `GET /movies/{id}` - To get a movie by id
* `POST /movies` - To create a movie
* `PATCH /movies/{id}` - To update a movie by id
* `DELETE /movies/{id}` - To delete a movie by id, (with `force=true` parameter to force deletion): 

#### Filters (can work with combinations)

* `GET /api/movies/search?title={Title name}` - Retrieve movies filtered by genre, can work with incomplete title names
* `GET /api/movies?genre={genreId}` - Retrieve movies filtered by genre
* `GET /api/movies?year={releaseYear}` - Retrieve movies filtered by release year
* `GET /api/movies?actor={Actor.id}` - Retrieve movies that the actor with the given id has

</details>

<details><summary> Actors </summary>

* `GET /actors` - To get all actors
* `GET /actors/{id}` - To get an actor by id
* `POST /actors` - To create an actor
* `PATCH /actors/{id}` - To update an actor by id
* `DELETE /actors/{id}` - To delete an actor by id, (with `force=true` parameter to force deletion)

### Filters

* `GET /api/movies/{movieId}/actors` - Retrieve all actors starring in a movie
* `GET /api/actors?name={name}`- Retrieve actors filtered by name, can work with incomplete names 

</details>


<details><summary> Genres </summary>

* `GET /genres` - To get all genres
* `GET /genres/{id}` - To get a genre by id
* `POST /genres`- To create a genre
* `PATCH /genres/{id}`- To update a genre by id
* `DELETE /genres/{id}`- To delete a genre by id (with `force=true` parameter to force deletion)

### Filters

* `GET /api/movies/{movieId}/genres`- Retrieve all genres starring in a movie
* `GET /api/genres?name={name}`- Retrieve genres filtered by name, can work with incomplete names

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
