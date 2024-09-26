package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {
    private IMovieService service;

    @GetMapping
    public List<Movie> findAllMovies() {
        return service.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie readMovieById(@PathVariable Long id) {
        return service.findMovieById(id);
    }

    @PostMapping("/create")
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie);
    }

    @PostMapping("/update")
    public Movie updateMovie(@RequestBody Movie movie) {
        return service.updateMovie(movie);
    }

    @GetMapping("/delete/{id}")
    public Boolean deleteMovieById(@PathVariable Long id) {
        return service.deleteMovieById(id);
    }
}


