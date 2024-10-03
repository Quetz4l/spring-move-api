package com.quetz4l.getflix.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MovieRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Min(value = 1, message = "Duration must be greater than 0")
    @Max(value = Integer.MAX_VALUE, message = "Duration must be smaller than" + Integer.MAX_VALUE)
    private int duration;

    @Min(value = 1600, message = "Year must be grater than 1895")
    private short releaseYear;


    private List<@Min(value = 1, message = "Genre ids must be greater than 0") Long> genres = new ArrayList<>();
    private List<@Min(value = 1, message = "Actor ids must be greater than 0") Long> actors = new ArrayList<>();

    public List<Long> getGenres() {
        return this.genres.stream().distinct().toList();
    }

    public List<Long> getActors() {
        return this.actors.stream().distinct().toList();
    }
}
