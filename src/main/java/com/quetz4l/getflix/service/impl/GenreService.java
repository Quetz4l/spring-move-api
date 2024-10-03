package com.quetz4l.getflix.service.impl;

import com.quetz4l.getflix.dto.request.GenreRequestDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.repository.GenreRepository;
import com.quetz4l.getflix.repository.MovieRepository;
import com.quetz4l.getflix.service.IGenreService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class GenreService implements IGenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    //CRUD
    @Override
    public List<Genre> findAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable).getContent();
    }

    @Override
    public Genre findGenreById(Long id) throws ResourceNotFound {
        return genreRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Genre not found"));
    }

    @Override
    public Genre createGenre(GenreRequestDTO genreRequestDTO) throws UnknownException, ResourceAlreadyExists {
        if (findGenreByName(genreRequestDTO.getName()).isPresent()) {
            throw new ResourceAlreadyExists("This genre already exists");
        }

        try {
            return genreRepository.save(convertDTOToGenre(new Genre(), genreRequestDTO));
        } catch (Exception ex) {
            throw new UnknownException("Genre could not be created");
        }

    }

    @Override
    public Genre updateGenre(Long id, GenreRequestDTO genreRequestDTO) throws ResourceNotFound, UnknownException {
        Genre newGenre = convertDTOToGenre(findGenreById(id), genreRequestDTO);

        try {
            return genreRepository.save(newGenre);
        } catch (Exception ex) {
            throw new UnknownException("Genre could not be updated");
        }
    }

    @Override
    public void deleteGenreById(Long id, boolean forceDelete) throws ResourceNotFound, DeletionIsImpossible, UnknownException {
        Genre genre = findGenreById(id);

        if (!forceDelete) {
            int countMovies = genre.getMovies().size();
            if (countMovies > 0) {
                throw new DeletionIsImpossible("Cannot delete genre '" + genre.getName() + "' because it has " + countMovies + " associated movie" + (countMovies == 1 ? "" : "s"));
            }
        }

        try {
            genreRepository.deleteById(id);
        } catch (Exception ex) {
            throw new UnknownException("Genre could not be deleted");
        }
    }


    //others...
    @Override
    public Optional<Genre> findGenreByName(String name) {
        return genreRepository.findAllByName(name);
    }


    public Genre convertDTOToGenre(Genre genre, GenreRequestDTO genreRequestDTO) {
        genre.setName(genreRequestDTO.getName());
        genre.clearMovies();

        if (!genreRequestDTO.getMovies().isEmpty()) {
            movieRepository.findAllById(genreRequestDTO.getMovies()).forEach(genre::addMovie);
        }

        return genre;
    }


}
