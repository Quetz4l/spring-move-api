package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Genre;

import java.util.List;

public interface IGenreService {
    List<Genre> findAllGenres();

    Genre findGenreById(Long id);

    Genre createGenre(Genre genre);

    Genre updateGenre(Genre genre);

    Boolean deleteGenreById(Long id);
}
