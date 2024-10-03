package com.quetz4l.getflix.service.impl;

import com.quetz4l.getflix.dto.request.ActorRequestDTO;
import com.quetz4l.getflix.exceptions.custom.DeletionIsImpossible;
import com.quetz4l.getflix.exceptions.custom.ResourceAlreadyExists;
import com.quetz4l.getflix.exceptions.custom.ResourceNotFound;
import com.quetz4l.getflix.exceptions.custom.UnknownException;
import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.repository.ActorRepository;
import com.quetz4l.getflix.repository.MovieRepository;
import com.quetz4l.getflix.service.IActorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class ActorService implements IActorService {
    private final EntityManager entityManager;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    //CRUD
    @Override
    public List<Actor> findAllActors(Pageable pageable, String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
        Root<Actor> actor = query.from(Actor.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.equals("null")) {
            predicates.add(cb.equal(actor.get("name"), name));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    @Override
    public Actor findActorById(Long id) throws ResourceNotFound {
        return actorRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Actor not found"));
    }

    @Override
    public Actor createActor(ActorRequestDTO actorDTO) throws ResourceAlreadyExists, UnknownException {
        List<Actor> actor = findAllByName(actorDTO.getName());
        Optional<Actor> foundedActor = actor.stream().filter(a -> a.getBirthDate().equals(actorDTO.getBirthDate())).findFirst();

        if (foundedActor.isPresent()) {
            throw new ResourceAlreadyExists("This actor already exists");
        }

        try {
            return actorRepository.save(convertDTOToActor(new Actor(), actorDTO));
        } catch (Exception ex) {
            throw new UnknownException("Actor could not be created");
        }
    }

    @Override
    public Actor updateActor(Long id, ActorRequestDTO actorDTO) throws ResourceNotFound, UnknownException {
        Actor modActor = convertDTOToActor(findActorById(id), actorDTO);
        try {
            return actorRepository.save(modActor);
        } catch (Exception ex) {
            throw new UnknownException("Actor could not be updated");
        }
    }

    @Override
    public void deleteActorById(Long id, boolean forceDeletion) throws DeletionIsImpossible, UnknownException, ResourceNotFound {
        Actor actor = findActorById(id);

        if (!forceDeletion && !actor.getMovies().isEmpty()) {
            int countMovies = actor.getMovies().size();
            throw new DeletionIsImpossible("Unable to delete actor '" + actor.getName() + "' as they are associated with " + countMovies + " movie" + (countMovies == 1 ? "" : "s"));
        }

        try {
            actorRepository.deleteById(id);
        } catch (Exception ex) {
            throw new UnknownException("Actor could not be updated");
        }

    }


    //others...
    @Override
    public List<Actor> findAllByName(String name) {
        return actorRepository.findAllByName(name);
    }

    public Actor convertDTOToActor(Actor actor, ActorRequestDTO actorDTO) {
        actor.setName(actorDTO.getName());
        actor.setBirthDate(actorDTO.getBirthDate());

        actor.clearMovies();

        if (!actorDTO.getMovies().isEmpty()) {
            movieRepository.findAllById(actorDTO.getMovies()).forEach(actor::addMovie);
        }

        return actor;
    }

}
