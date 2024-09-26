package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IMovieService {
    List<Movie> findAllMovies();

    Optional<Movie> findMovieById(Long id);

    Optional<Movie> createMovie(Movie movie);

    Optional<Movie> updateMovie(Movie movie);

    Boolean deleteMovieById(Long id);
}
