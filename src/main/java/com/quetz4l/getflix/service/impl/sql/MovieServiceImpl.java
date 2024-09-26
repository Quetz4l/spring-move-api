package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.repository.IMovieRepository;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class MovieServiceImpl implements IMovieService {
    private IMovieRepository repository;

    @Override
    public List<Movie> findAllMovies() {
        return repository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Movie> createMovie(Movie movie) {
        return Optional.of(repository.save(movie));
    }

    @Override
    public Optional<Movie> updateMovie(Movie movie) {
        return Optional.of(repository.save(movie));
    }

    @Override
    public Boolean deleteMovieById(Long id) {
        if (findMovieById(id).isEmpty()) return false;
        repository.deleteById(id);
        return findMovieById(id).isEmpty();
    }
}
