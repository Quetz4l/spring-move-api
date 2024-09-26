package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.service.IGenreService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class GenreServiceImpl implements IGenreService {
    private IGenreService genreService;

    @Override
    public List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreService.findGenreById(id);
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreService.createGenre(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreService.updateGenre(genre);
    }

    @Override
    public Boolean deleteGenreById(Long id) {
        return genreService.deleteGenreById(id);
    }
}
