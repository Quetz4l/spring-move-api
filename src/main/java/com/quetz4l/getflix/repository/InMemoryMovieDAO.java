package com.quetz4l.getflix.repository;

import com.quetz4l.getflix.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMovieDAO {
    private final List<Movie> MOVIES = new ArrayList<>();

    private final Long lastId = 0L;

    public List<Movie> findAllMovies() {
        return MOVIES;
    }

    public Movie findMovieById(Long id) {
        return MOVIES.stream().filter(movie -> movie.getId().equals(id)).findFirst().orElse(null);
    }

    public Movie saveMovie(Movie movie) {
        return MOVIES.stream().filter(s -> s.getId().equals(movie.getId())).findFirst().orElse(null);
    }

    public Movie updateMovie(Movie movie) {
        return MOVIES.stream()
                .filter(s -> s.getId().equals(movie.getId()))
                .findFirst()
                .map(s -> {
                    int idx = MOVIES.indexOf(s);
                    MOVIES.set(idx, movie);
                    return movie;
                })
                .orElse(null);
    }

    public boolean deleteMovieById(Long id) {
        return MOVIES.removeIf(s -> s.getId().equals(id));
    }
}



