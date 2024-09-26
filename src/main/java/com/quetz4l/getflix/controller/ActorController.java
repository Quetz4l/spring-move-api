package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.service.IActorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/actor")
public class ActorController {
    private IActorService actorService;

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor).orElse(null);
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    public Actor findActorById(@PathVariable Long id) {
        return actorService.findActorById(id).orElse(null);
    }

    @PatchMapping("/{id}")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.updateActor(actor).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteActorById(@PathVariable Long id) {
        return actorService.deleteActorById(id);
    }

}
