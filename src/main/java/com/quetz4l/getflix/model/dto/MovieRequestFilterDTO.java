package com.quetz4l.getflix.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestFilterDTO {
    @Size(min = 1895, max = 2024, message = "Year must be between 1895 and 2024")
    private short year;

    @Size(min = 1, message = "Actor id must be greater than 0")
    private long actor;

    @Size(min = 1, message = "Genre id must be greater than 0")
    private long genre;
}
