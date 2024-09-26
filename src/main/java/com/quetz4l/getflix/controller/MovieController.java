package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
    private IMovieService service;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie).orElse(null);
    }

    @GetMapping
    public List<Movie> findAllMovies(
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) short year,
            @RequestParam(required = false) Long actorId
    ) {
        return service.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie readMovieById(@PathVariable Long id) {
        return service.findMovieById(id).orElse(null);
    }

    @PatchMapping("/{id}")
    public Movie updateMovie(@RequestBody Movie movie) {
        return service.updateMovie(movie).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMovieById(@PathVariable Long id) {
        return service.deleteMovieById(id);
    }


    @GetMapping
    public List<Movie> findAllMoviesFilteredByGenre(@RequestParam(required = false) Long genreId) {
        return new ArrayList<>();
    }

//    private List<Movie> getMovies(Long genreId, short year, Long actorId) {
//        List<Movie> result = new ArrayList<>();
//
//        if (genreId != null) service.filterByGenre(genreId);
//        if ()
//
//        return result;
//    }

}


