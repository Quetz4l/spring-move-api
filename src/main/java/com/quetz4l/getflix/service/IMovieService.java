package com.quetz4l.getflix.service;

import com.quetz4l.getflix.dto.request.MovieRequestDTO;
import com.quetz4l.getflix.dto.request.MovieRequestFilterDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovieService {
    //CRUD
    List<Movie> findAllMovies(MovieRequestFilterDTO filter, Pageable pageable);

    Movie findMovieById(Long id) throws ResourceNotFound;

    Movie createMovie(MovieRequestDTO movie) throws ResourceAlreadyExists, UnknownException;

    Movie updateMovie(Long id, MovieRequestDTO movieRequestDT) throws ResourceNotFound, UnknownException;

    void deleteMovieById(Long id, boolean forceDeletion) throws ResourceNotFound, DeletionIsImpossible, UnknownException;

    //others
    List<Actor> findActorsByMovieId(Long id);

    List<Movie> findMoviesByTitle(String title);
}
