package com.quetz4l.getflix.repository;

import com.quetz4l.getflix.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRepository extends JpaRepository<Actor, Long> {
}
