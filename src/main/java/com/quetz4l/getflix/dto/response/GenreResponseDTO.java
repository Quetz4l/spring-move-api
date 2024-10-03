package com.quetz4l.getflix.dto.response;

import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GenreResponseDTO {
    private Long id;
    private String name;

    private List<Long> movies = new ArrayList<>();

    public GenreResponseDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();

        if (genre.getMovies() != null) {
            this.movies = genre.getMovies().stream().map(Movie::getId).toList();
        }
    }
}
