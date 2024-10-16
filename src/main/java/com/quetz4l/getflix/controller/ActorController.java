package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.dto.request.ActorRequestDTO;
import com.quetz4l.getflix.dto.response.ActorResponseDTO;
import com.quetz4l.getflix.dto.response.SuccessfulResponse;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.NotBooleanType;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.service.IActorService;
import com.quetz4l.getflix.util.Bool;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

import static com.quetz4l.getflix.validation.IdValidator.GREATER_ID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {
    private final IActorService service;

    //CRUD
    @GetMapping
    public SuccessfulResponse getAllActors(
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "Minimum page value is 1") short page,
            @RequestParam(value = "size", defaultValue = "10") @Min(value = 1, message = "Minimum size value is 1")
            @Max(value = 100, message = "Maximum size value is 100") short size,
            @RequestParam(value = "name", defaultValue = "null")
            @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters") String name
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new SuccessfulResponse(service.findAllActors(pageable, name).stream().map(ActorResponseDTO::new).toList());
    }

    @GetMapping("{id}")
    public SuccessfulResponse findActorById(@PathVariable @Min(value = 1, message = GREATER_ID) Long id) throws ResourceNotFound {
        Actor actorById = service.findActorById(id);
        return new SuccessfulResponse(actorById);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessfulResponse createActor(@Valid @RequestBody ActorRequestDTO actorDTO) throws ResourceAlreadyExists, UnknownException {
        ActorResponseDTO actorResponseDTO = new ActorResponseDTO(service.createActor(actorDTO));
        return new SuccessfulResponse(actorResponseDTO);
    }

    @PatchMapping("/{id}")
    public SuccessfulResponse updateActor(@PathVariable @Min(value = 1, message = GREATER_ID) Long id, @Valid @RequestBody ActorRequestDTO actorDTO) throws ResourceNotFound, UnknownException {
        return new SuccessfulResponse(service.updateActor(id, actorDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActorById(@PathVariable @Min(value = 1, message = GREATER_ID) Long id, @RequestParam(defaultValue = "false") String force) throws ResourceNotFound, UnknownException, DeletionIsImpossible, NotBooleanType {
        boolean forceDeletion = Bool.parseBoolean(force);
        service.deleteActorById(id, forceDeletion);
    }

}
