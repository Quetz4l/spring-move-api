package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.dto.GenreRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IGenreService {
    List<Genre> findAllGenres();

    Optional<Genre> findGenreById(Long id);

    Optional<Genre> createGenre(GenreRequestDTO genreRequestDTO);

    Optional<Genre> updateGenre(Genre genre);

    Boolean deleteGenreById(Long id);

    List<Genre> findAllById(List<Long> ids);
}
