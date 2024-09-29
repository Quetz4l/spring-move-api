package com.quetz4l.getflix.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ActorRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Birth is required")
    @Past(message = "Birth date cannot be in the past")
    private LocalDate birthDate;

//    private List<@Size(min = 1, message = "Movie id must be greater than 0") Long> moviesId;

}
