package com.quetz4l.getflix.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GenreRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String name;

    private List<@Min(value = 1, message = "Movie id must be greater than 0") Long> movies = new ArrayList<>();

    public List<@Min(value = 1, message = "Movie id must be greater than 0") Long> getMovies() {
        return this.movies.stream().distinct().toList();
    }
}
