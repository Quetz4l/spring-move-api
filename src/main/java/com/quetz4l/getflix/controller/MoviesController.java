package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.model.dto.MovieRequestFilterDTO;
import com.quetz4l.getflix.service.IMoviesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//GET /api/movies?genre={genreId}: Retrieve movies filtered by genre
//GET /api/movies?year={releaseYear}: Retrieve movies filtered by release year
//GET /api/movies?actor={Actor.id}: Retrieve movies that the actor with the given id has starred in
//GET /api/movies/{movieId}/actors: Retrieve all actors starring in a movie

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {
    private IMoviesService moviesService;

    @GetMapping

    public List<Movie> findAllByFilter(@ModelAttribute MovieRequestFilterDTO filter) {
//        return moviesService.findMoviesByFilter(filter.getYear(), filter.getGenre(), filter.getActor());
        return moviesService.findMoviesByFilter(filter.getYear(), filter.getGenre());
//        return moviesService.findMoviesByFilter(filter.getYear());
//        return new ArrayList<>();
    }

    @GetMapping("/{id}/actors")
    public List<Actor> findActorsByMovieId(@PathVariable Long id) {
        return null;
    }


}
