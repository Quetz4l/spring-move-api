package com.quetz4l.getflix.dto.response;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieResponseDTO {
    private Long id;
    private String title;
    private short releaseYear;
    private int duration;

    private List<Long> actors = new ArrayList<>();
    private List<Long> genres = new ArrayList<>();

    public MovieResponseDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.duration = movie.getDuration();

        if (movie.getActors() != null) {
            this.actors = movie.getActors().stream().map(Actor::getId).toList();
        }

        if (movie.getGenres() != null) {
            this.genres = movie.getGenres().stream().map(Genre::getId).toList();
        }
    }

}
