package com.quetz4l.getflix.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MovieRequestFilterDTO {
    @Size(min = 1895, max = 2024, message = "Year must be between 1895 and 2024")
    private short releaseYear;

    @Size(min = 1, message = "Actor id must be greater than 0")
    private long actorId;

    @Size(min = 1, message = "Genre id must be greater than 0")
    private long genreId;
}
