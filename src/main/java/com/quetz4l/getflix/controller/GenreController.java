package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.service.IGenreService;
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
@RequestMapping("/api/v1/genre")
public class GenreController {
    private IGenreService genreService;

    public List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genre findGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id);
    }

    @PostMapping("/create")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PostMapping("/update")
    public Genre updateGenre(@RequestBody Genre genre) {
        return genreService.updateGenre(genre);
    }

    @GetMapping("/delete/{id}")
    public Boolean deleteGenreById(@PathVariable Long id) {
        return genreService.deleteGenreById(id);
    }


}
