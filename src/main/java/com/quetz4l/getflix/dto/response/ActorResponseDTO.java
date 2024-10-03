package com.quetz4l.getflix.dto.response;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActorResponseDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;

    private List<Long> movies = new ArrayList<>();

    public ActorResponseDTO(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.birthDate = actor.getBirthDate();

        if (actor.getMovies() != null) {
            this.movies = actor.getMovies().stream().map(Movie::getId).toList();
        }
    }
}
