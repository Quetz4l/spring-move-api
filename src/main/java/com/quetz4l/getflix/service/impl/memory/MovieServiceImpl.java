package com.quetz4l.getflix.service.impl.memory;

import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.repository.InMemoryMovieDAO;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private InMemoryMovieDAO repository;

    @Override
    public List<Movie> findAllMovies() {
        return repository.findAllMovies();
    }

    @Override
    public Movie findMovieById(Long id) {
        return repository.findMovieById(id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repository.saveMovie(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return repository.updateMovie(movie);
    }

    @Override
    public Boolean deleteMovieById(Long id) {
        return repository.deleteMovieById(id);
    }
}
