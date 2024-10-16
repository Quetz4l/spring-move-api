package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.dto.request.GenreRequestDTO;
import com.quetz4l.getflix.dto.response.GenreResponseDTO;
import com.quetz4l.getflix.dto.response.SuccessfulResponse;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.NotBooleanType;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.service.IGenreService;
import com.quetz4l.getflix.util.Bool;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final IGenreService service;

    @GetMapping
    public SuccessfulResponse findAllGenres(
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "Minimum page value is 1") short page,
            @RequestParam(value = "size", defaultValue = "10") @Min(value = 1, message = "Minimum size value is 1")
            @Max(value = 100, message = "Maximum size value is 100") short size,
            @NotNull @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters") String name
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new SuccessfulResponse(service.findAllGenres(pageable, name).stream().map(GenreResponseDTO::new).toList());
    }

    @GetMapping("/{id}")
    public SuccessfulResponse findGenreById(@PathVariable Long id) throws ResourceNotFound {
        return new SuccessfulResponse(service.findGenreById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessfulResponse createGenre(@Valid @RequestBody GenreRequestDTO genreRequestDTO) throws ResourceAlreadyExists, UnknownException {
        return new SuccessfulResponse(service.createGenre(genreRequestDTO));
    }

    @PatchMapping("/{id}")
    public SuccessfulResponse updateGenre(@PathVariable Long id, @RequestBody GenreRequestDTO genreRequestDTO) throws ResourceNotFound, UnknownException {
        return new SuccessfulResponse(new GenreResponseDTO(service.updateGenre(id, genreRequestDTO)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenreById(@PathVariable Long id, @RequestParam(defaultValue = "false") String force) throws ResourceNotFound, UnknownException, DeletionIsImpossible, NotBooleanType {
        boolean forceDeletion = Bool.parseBoolean(force);
        service.deleteGenreById(id, forceDeletion);
    }


}
