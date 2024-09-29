package com.quetz4l.getflix.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Duration is required")
    @Size(min = 1, message = "Duration must be greater than 0")
    private int duration;

    private List<@Size(min = 1, message = "Genre ids must be greater than 0") Long> genres;
    private List<@Size(min = 1, message = "Actor ids must be greater than 0") Long> actors;

    @NotBlank(message = "Release year is required")
    @Size(min = 1895, max = 2024, message = "Year must be between 1895 and 2024")
    private short releaseYear;
}
