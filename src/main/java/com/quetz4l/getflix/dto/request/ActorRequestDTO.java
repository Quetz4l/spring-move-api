package com.quetz4l.getflix.dto.request;

import com.quetz4l.getflix.validation.birthdate.ValidBirthDateFormat;
import com.quetz4l.getflix.validation.birthdate.ValidBirthDatePast;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ActorRequestDTO {

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must be between 3 and 100 characters")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name should contains only letters and spaces")
    private String name;


    @NotNull(message = "Birth date is required")
    @ValidBirthDatePast
    @ValidBirthDateFormat
    private String birthDate;

    private List<@Min(value = 1, message = "Movie ids must be greater than 0") Long> movies = new ArrayList<>();


    public LocalDate getBirthDate() {
        return LocalDate.parse(birthDate);
    }

    public List<@Min(value = 1, message = "Movie ids must be greater than 0") Long> getMovies() {
        return this.movies.stream().distinct().toList();
    }
}
