package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.service.IActorService;

import java.util.List;

public class ActorServiceImpl implements IActorService {
    private IActorService actorService;

    @Override
    public List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @Override
    public Actor findActorById(Long id) {
        return actorService.findActorById(id);
    }

    @Override
    public Actor createActor(Actor actor) {
        return actorService.createActor(actor);
    }

    @Override
    public Actor updateActor(Actor actor) {
        return actorService.updateActor(actor);
    }

    @Override
    public Boolean deleteActorById(Long id) {
        return actorService.deleteActorById(id);
    }
}
