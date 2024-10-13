package com.quetz4l.getflix.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestFilterDTO {
    @Min(value = 1895, message = "Year must be less than 2024")
    @Max(value = 2024, message = "Year must be greater than 2024")
    private short year;

    @Min(value = 1, message = "Actor id must be greater than 0")
    private long actor;

    @Min(value = 1, message = "Genre id must be greater than 0")
    private long genre;
}
