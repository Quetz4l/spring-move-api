package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.repository.IMovieRepository;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Movie findMovieById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Boolean deleteMovieById(Long id) {
        if (findMovieById(id) == null) return false;
        repository.deleteById(id);
        return findMovieById(id) == null;
    }
}
