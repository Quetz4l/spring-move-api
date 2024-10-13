package com.quetz4l.getflix.service.impl;

import com.quetz4l.getflix.dto.request.MovieRequestDTO;
import com.quetz4l.getflix.dto.request.MovieRequestFilterDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.repository.ActorRepository;
import com.quetz4l.getflix.repository.GenreRepository;
import com.quetz4l.getflix.repository.MovieRepository;
import com.quetz4l.getflix.service.IMovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
public class MovieService implements IMovieService {
    private final EntityManager entityManager;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<Movie> findAllMovies(MovieRequestFilterDTO filter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> movie = query.from(Movie.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getYear() != 0) {
            predicates.add(cb.equal(movie.get("releaseYear"), filter.getYear()));
        }

        if (filter.getGenre() != 0) {
            Join<Movie, Genre> genreJoin = movie.join("genres");
            predicates.add(cb.equal(genreJoin.get("id"), filter.getGenre()));
        }


        if (filter.getActor() != 0) {
            Join<Movie, Actor> actorsJoin = movie.join("actors");
            predicates.add(cb.equal(actorsJoin.get("id"), filter.getActor()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

    }

    @Override
    public Movie findMovieById(Long id) throws ResourceNotFound {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Movie not found"));
    }

    @Override
    public Movie createMovie(MovieRequestDTO movieDTO) throws ResourceAlreadyExists, UnknownException {
        List<Movie> foundedMovies = findMoviesByTitle(movieDTO.getTitle());

        if (!foundedMovies.isEmpty()) {
            Optional<Movie> foundedMovie = foundedMovies.stream().filter(movie -> movie.getReleaseYear() == movieDTO.getReleaseYear() && movie.getDuration() == movieDTO.getDuration()).findFirst();
            if (foundedMovie.isPresent()) {
                throw new ResourceAlreadyExists("Movie with title " + movieDTO.getTitle() + " already exists");
            }
        }

        try {
            return movieRepository.save(convertDTOToMovie(new Movie(), movieDTO));
        } catch (Exception ex) {
            throw new UnknownException("Movie could not be created");
        }
    }

    @Override
    public Movie updateMovie(Long id, MovieRequestDTO movieRequestDTO) throws ResourceNotFound, UnknownException {
        Movie movie = convertDTOToMovie(findMovieById(id), movieRequestDTO);
        try {
            return movieRepository.save(movie);
        } catch (Exception ex) {
            throw new UnknownException("Movie could not be created");
        }
    }

    @Override
    public void deleteMovieById(Long id, boolean forceDeletion) throws ResourceNotFound, DeletionIsImpossible, UnknownException {
        Movie movie = findMovieById(id);

        if (!forceDeletion) {
            int countActors = movie.getActors().size();
            if (countActors > 0) {
                throw new DeletionIsImpossible("Cannot delete genre '" + movie.getTitle() + "' because it has " + countActors + " associated actor" + (countActors == 1 ? "" : "s"));
            }

            int countGenres = movie.getGenres().size();
            if (countGenres > 0) {
                throw new DeletionIsImpossible("Cannot delete genre '" + movie.getTitle() + "' because it has " + countGenres + " associated genre" + (countGenres == 1 ? "" : "s"));
            }
        }

        try {
            movieRepository.deleteById(id);
        } catch (Exception ex) {
            throw new UnknownException("Movie could not be deleted");
        }

    }

    //others...
    @Override
    public List<Actor> findActorsByMovieId(Long id) throws ResourceNotFound {
        return findMovieById(id).getActors();
    }

    @Override
    public List<Genre> findGenresByMovieId(Long id) throws ResourceNotFound {
        return findMovieById(id).getGenres();
    }

    @Override
    public List<Movie> findMoviesByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }

    @Override
    public List<Movie> findActorsByMovieTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> movie = query.from(Movie.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(movie.get("title"), "%" + title + "%"));
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }


    public Movie convertDTOToMovie(Movie movie, MovieRequestDTO movieRequestDTO) {
        movie.setTitle(movieRequestDTO.getTitle());
        movie.setReleaseYear(movieRequestDTO.getReleaseYear());
        movie.setDuration(movieRequestDTO.getDuration());
        movie.clearGenre();
        movie.clearActor();


        if (!movieRequestDTO.getGenres().isEmpty()) {
            movie.setGenres(genreRepository.findAllById(movieRequestDTO.getGenres()));
        }
        if (!movieRequestDTO.getActors().isEmpty()) {
            movie.setActors(actorRepository.findAllById(movieRequestDTO.getActors()));
        }

        return movie;
    }


}
