package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.dto.GenreRequestDTO;
import com.quetz4l.getflix.service.IGenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genre")
public class GenreController {
    private IGenreService genreService;

    @PostMapping
    public Genre createGenre(@Valid @RequestBody GenreRequestDTO genreRequestDTO) {
        return genreService.createGenre(genreRequestDTO).orElse(null);
    }

    @GetMapping
    public List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genre findGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id).orElse(null);
    }

    @PatchMapping("/{id}")
    public Genre updateGenre(@RequestBody Genre genre) {
        return genreService.updateGenre(genre).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteGenreById(@PathVariable Long id) {
        return genreService.deleteGenreById(id);
    }


}
