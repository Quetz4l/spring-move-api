package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.model.dto.MovieRequestDTO;
import com.quetz4l.getflix.model.dto.MovieRequestFilterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IMovieService {
    List<Movie> findAllMovies(MovieRequestFilterDTO movieRequestFilterDTO);

    Optional<Movie> findMovieById(Long id);

    Optional<Movie> createMovie(MovieRequestDTO movie);

    Optional<Movie> updateMovie(Long id, MovieRequestDTO movieRequestDT);

    Boolean deleteMovieById(Long id);
}
