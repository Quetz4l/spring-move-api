package com.quetz4l.getflix.service;

import com.quetz4l.getflix.dto.request.GenreRequestDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IGenreService {
    //CRUD
    List<Genre> findAllGenres(Pageable pageable);

    Genre findGenreById(Long id) throws ResourceNotFound;

    Genre createGenre(GenreRequestDTO genreRequestDTO) throws UnknownException, ResourceAlreadyExists;

    Genre updateGenre(Long id, GenreRequestDTO genreRequestDTO) throws ResourceNotFound, UnknownException;

    void deleteGenreById(Long id, boolean forceDelete) throws ResourceNotFound, DeletionIsImpossible, UnknownException;

    //others
    Optional<Genre> findGenreByName(String name);

    Genre convertDTOToGenre(Genre genre, GenreRequestDTO genreRequestDTO);
}
