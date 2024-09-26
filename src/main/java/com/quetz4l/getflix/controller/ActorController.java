package com.quetz4l.getflix.controller;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.service.IActorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/actor")
public class ActorController {
    private IActorService actorService;

    public List<Actor> getAllActors(){
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    public Actor findActorById(@PathVariable Long id) {
        return actorService.findActorById(id);
    }

    @PostMapping("/create")
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @PostMapping("/update")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.updateActor(actor);
    }

    @GetMapping("/remove/{id}")
    public Boolean deleteActorById(@PathVariable Long id) {
        return actorService.deleteActorById(id);
    }


}
