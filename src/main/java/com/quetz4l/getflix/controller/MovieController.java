package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.dto.request.MovieRequestDTO;
import com.quetz4l.getflix.dto.request.MovieRequestFilterDTO;
import com.quetz4l.getflix.dto.response.ActorResponseDTO;
import com.quetz4l.getflix.dto.response.MovieResponseDTO;
import com.quetz4l.getflix.dto.response.SuccessfulResponse;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.NotBooleanType;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.service.IMovieService;
import com.quetz4l.getflix.util.Bool;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.quetz4l.getflix.validation.IdValidator.GREATER_ID;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private IMovieService service;

    @GetMapping
    public SuccessfulResponse findAllMovies(
            @ModelAttribute MovieRequestFilterDTO filter,
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "Minimum page value is 1") short page,
            @RequestParam(value = "size", defaultValue = "10") @Min(value = 1, message = "Minimum size value is 1")
            @Max(value = 100, message = "Maximum size value is 100") short size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new SuccessfulResponse(service.findAllMovies(filter, pageable).stream().map(MovieResponseDTO::new).toList());
    }


    @GetMapping("/{id}")
    public SuccessfulResponse findMovieById(@PathVariable @Min(value = 1, message = GREATER_ID) Long id) throws ResourceNotFound {
        return new SuccessfulResponse(service.findMovieById(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessfulResponse createMovie(@Valid @RequestBody MovieRequestDTO movieDTO) throws ResourceAlreadyExists, UnknownException {
        return new SuccessfulResponse(service.createMovie(movieDTO));
    }


    @PatchMapping("/{id}")
    public SuccessfulResponse updateMovie(@PathVariable @Min(value = 1, message = GREATER_ID) Long id, @RequestBody MovieRequestDTO movieRequestDTO) throws ResourceNotFound, UnknownException {
        return new SuccessfulResponse(service.updateMovie(id, movieRequestDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable @Min(value = 1, message = GREATER_ID) Long id, @RequestParam(defaultValue = "false") String force) throws ResourceNotFound, UnknownException, DeletionIsImpossible, NotBooleanType {
        boolean forceDeletion = Bool.parseBoolean(force);
        service.deleteMovieById(id, forceDeletion);
    }
    //others...


    @GetMapping("/{id}/actors")
    public SuccessfulResponse findActorsByMovieId(@PathVariable @Min(value = 1, message = GREATER_ID) Long id) {
        return new SuccessfulResponse(service.findActorsByMovieId(id).stream().map(ActorResponseDTO::new).toList());
    }

}