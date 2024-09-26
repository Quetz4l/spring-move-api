package com.quetz4l.getflix.service.impl.sql;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.repository.IActorRepository;
import com.quetz4l.getflix.service.IActorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class ActorServiceImpl implements IActorService {
    private IActorRepository repository;

    @Override
    public List<Actor> findAllActors() {
        return repository.findAll();
    }

    @Override
    public Optional<Actor> findActorById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Actor> createActor(Actor actor) {
        return Optional.of(repository.save(actor));
    }

    @Override
    public Optional<Actor> updateActor(Actor actor) {
        return Optional.of(repository.save(actor));
    }

    @Override
    public Boolean deleteActorById(Long id) {
        if (findActorById(id).isEmpty()) return false;
        repository.deleteById(id);
        return findActorById(id).isEmpty();
    }
}
