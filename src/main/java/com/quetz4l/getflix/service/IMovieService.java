package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovieService {
    List<Movie> findAllMovies();

    Movie findMovieById(Long id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    Boolean deleteMovieById(Long id);
}
