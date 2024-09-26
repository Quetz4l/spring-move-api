package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IGenreService {
    List<Genre> findAllGenres();

    Optional<Genre> findGenreById(Long id);

    Optional<Genre> createGenre(Genre genre);

    Optional<Genre> updateGenre(Genre genre);

    Boolean deleteGenreById(Long id);
}
