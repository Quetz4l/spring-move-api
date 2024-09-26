package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.repository.IGenreRepository;
import com.quetz4l.getflix.service.IGenreService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class GenreServiceImpl implements IGenreService {
    private IGenreRepository repository;

    @Override
    public List<Genre> findAllGenres() {
        return repository.findAll();
    }

    @Override
    public Optional<Genre> findGenreById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Genre> createGenre(Genre genre) {
        return Optional.of(repository.save(genre));
    }

    @Override
    public Optional<Genre> updateGenre(Genre genre) {
        return Optional.of(repository.save(genre));
    }

    @Override
    public Boolean deleteGenreById(Long id) {
        if (findGenreById(id).isEmpty()) return false;
        repository.deleteById(id);
        return findGenreById(id).isEmpty();
    }
}
