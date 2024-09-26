package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Actor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IActorService {
    List<Actor> findAllActors();

    Optional<Actor> findActorById(Long id);

    Optional<Actor> createActor(Actor actor);

    Optional<Actor> updateActor(Actor actor);

    Boolean deleteActorById(Long id);
}
