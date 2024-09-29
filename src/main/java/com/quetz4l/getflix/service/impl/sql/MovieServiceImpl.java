package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.model.dto.MovieRequestDTO;
import com.quetz4l.getflix.model.dto.MovieRequestFilterDTO;
import com.quetz4l.getflix.repository.IActorRepository;
import com.quetz4l.getflix.repository.IGenreRepository;
import com.quetz4l.getflix.repository.IMovieRepository;
import com.quetz4l.getflix.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
public class MovieServiceImpl implements IMovieService {
    private IMovieRepository movieRepository;
    private IGenreRepository genreRepository;
    private IActorRepository actorRepository;

    @Override
    public List<Movie> findAllMovies(MovieRequestFilterDTO movieRequestFilterDTO) {
        return findAllMoviesHelper(movieRequestFilterDTO);
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // TODO: check movie exist before creating
    @Override
    public Optional<Movie> createMovie(MovieRequestDTO movieDTO) {
        return Optional.of(movieRepository.save(convertDTOToMovie(movieDTO)));
    }

    @Override
    public Optional<Movie> updateMovie(Long id, MovieRequestDTO movieRequestDTO) {
        if (findMovieById(id).isEmpty()) return Optional.empty(); // TODO: return error message

        Movie movie = convertDTOToMovie(movieRequestDTO);
        movie.setId(id);

        return Optional.of(movieRepository.save(movie));
    }

    @Override
    public Boolean deleteMovieById(Long id) {
        if (findMovieById(id).isEmpty()) return false;
        movieRepository.deleteById(id);
        return findMovieById(id).isEmpty();
    }

    public Movie convertDTOToMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = new Movie();

        movie.setTitle(movieRequestDTO.getTitle());
        movie.setReleaseYear(movieRequestDTO.getReleaseYear());
        movie.setDuration(movieRequestDTO.getDuration());

        if (movieRequestDTO.getGenres() != null) {
            List<Genre> genres = genreRepository.findAllById(movieRequestDTO.getGenres());
            movie.setGenres(genres);
        } else {
            movie.setGenres(new ArrayList<>());
        }

        if (movieRequestDTO.getActors() != null) {
            List<Actor> actors = actorRepository.findAllById(movieRequestDTO.getActors());
            movie.setActors(actors);
        } else {
            movie.setActors(new ArrayList<>());
        }

        return movie;
    }

    private List<Movie> findAllMoviesHelper(MovieRequestFilterDTO filterDTO) {
//        if (filterDTO.getGenreId() > 0 && filterDTO.getReleaseYear() > 0 && filterDTO.getActorId() > 0) {
//            System.out.println("movieRequestFilterDTO 1");
//            return repository.getMovieByGenreId(filterDTO.getGenreId());
//        } else {
        System.out.println("movieRequestFilterDTO 2");
        return movieRepository.findAll();
//        }
    }

}
