package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.model.dto.MovieRequestDTO;
import com.quetz4l.getflix.model.dto.MovieRequestFilterDTO;
import com.quetz4l.getflix.service.IMovieService;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
    private IMovieService service;

    @PostMapping
    public Movie createMovie(@RequestBody MovieRequestDTO movie) {
        return service.createMovie(movie).orElse(null);
    }

    @GetMapping
    public List<Movie> findAllMovies(@ModelAttribute MovieRequestFilterDTO movieRequestFilterDTO) {
        return service.findAllMovies(movieRequestFilterDTO);
    }

    @GetMapping("/{id}")
    public Movie readMovieById(@PathVariable Long id) {
        return service.findMovieById(id).orElse(null);
    }

    @PatchMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        return service.updateMovie(id, movieRequestDTO).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMovieById(@Size(min = 1, message = "Movie id must be greater than 0") @PathVariable Long id) {
        return service.deleteMovieById(id);
    }

}


