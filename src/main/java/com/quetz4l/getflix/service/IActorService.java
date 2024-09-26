package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Actor;

import java.util.List;

public interface IActorService {
    List<Actor> findAllActors();

    Actor findActorById(Long id);

    Actor createActor(Actor actor);

    Actor updateActor(Actor actor);

    Boolean deleteActorById(Long id);
}
