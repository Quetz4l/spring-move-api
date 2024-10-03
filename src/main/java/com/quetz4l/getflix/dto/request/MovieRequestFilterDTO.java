package com.quetz4l.getflix.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestFilterDTO {
    @Min(value = 1895, message = "Year must be less than 2024")
    @Max(value = 2024, message = "Year must be greater than 2024")
    private short year;

    @NotBlank
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @Min(value = 1, message = "Actor id must be greater than 0")
    private long actor;

    @Min(value = 1, message = "Genre id must be greater than 0")
    private long genre;
}
