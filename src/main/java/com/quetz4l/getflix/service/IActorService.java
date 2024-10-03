package com.quetz4l.getflix.service;

import com.quetz4l.getflix.dto.request.ActorRequestDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Actor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IActorService {
    //CRUD
    List<Actor> findAllActors(Pageable pageable, String name);

    Actor findActorById(Long id) throws ResourceNotFound;

    Actor createActor(ActorRequestDTO actorDTO) throws ResourceAlreadyExists, UnknownException;

    Actor updateActor(Long id, ActorRequestDTO actorDTO) throws ResourceNotFound, UnknownException;

    void deleteActorById(Long id, boolean forceDeletion) throws DeletionIsImpossible, UnknownException, ResourceNotFound;

    //others
    List<Actor> findAllByName(String name);
}
