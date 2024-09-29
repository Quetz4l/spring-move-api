package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.dto.GenreRequestDTO;
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
    private IGenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Optional<Genre> createGenre(GenreRequestDTO genreRequestDTO) {
        return Optional.of(genreRepository.save(convertDTOToGenre(genreRequestDTO)));
    }

    @Override
    public Optional<Genre> updateGenre(Genre genre) {
        return Optional.of(genreRepository.save(genre));
    }

    @Override
    public Boolean deleteGenreById(Long id) {
        if (findGenreById(id).isEmpty()) return false;
        genreRepository.deleteById(id);
        return findGenreById(id).isEmpty();
    }

    @Override
    public List<Genre> findAllById(List<Long> ids) {
        return genreRepository.findAllById(ids);
    }

    public Genre convertDTOToGenre(GenreRequestDTO genreRequestDTO) {
        Genre genre = new Genre();
        genre.setName(genreRequestDTO.getName());
        return genre;
    }


}
