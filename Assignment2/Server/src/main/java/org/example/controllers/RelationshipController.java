package org.example.controllers;

import org.example.entities.Relationship;
import org.example.services.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/relationship")
public class RelationshipController
{
    @Autowired
    private RelationshipService rs;
    @PostMapping("create/{id1}/{id2}/{rating}") //id1 = consumer_id   //id2 = media_id
    private Mono<Relationship> createRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2, @PathVariable("rating") int rating)
    {
        Relationship  r = new Relationship();
        r.setConsumerId(id1);
        r.setMediaId(id2);
        r.setRating(rating);
        return rs.createRelationship(r);
    }

    // Read all media for consumer id
    @GetMapping("/consumer/{id}")
    private Flux<Long> getAllMediasByConsumerID(@PathVariable("id")Long id)
    {
        return rs.getAllMediasByConsumerID(id);
    }

    // Read all consumers by media id
    @GetMapping("/media/{id}")
    private Flux<Long> getAllConsumersByMediaID(@PathVariable("id")Long id)
    {
        return rs.getAllConsumersByMediaID(id);
    }

    @GetMapping("/getAllRelationships")
    private Flux<Relationship> getAllRelationships()
    {
        return rs.getAllRelationships();
    }

    @DeleteMapping("/delete/{id1}/{id2}")
    private Mono<Void> deleteRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        return rs.deleteRelationship(id1,id2);
    }
}
