package org.example.controllers;

import org.example.dtos.ConsumerWithMedia;
import org.example.entities.Consumer;
import org.example.entities.Relationship;
import org.example.services.ConsumerService;
import org.example.services.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consumer")
public class ConsumerController
{
    @Autowired
    private ConsumerService cs;
    @Autowired
    private RelationshipService rs;
    @PostMapping
    private Mono<Consumer> saveConsumer(@RequestBody Consumer c)
    {
        return cs.saveConsumer(c);
    }
    @GetMapping
    private Flux<Consumer> getAllConsumers()
    {
        return cs.getAllConsumers();
    }
    @GetMapping("/with-media")
    public Flux<ConsumerWithMedia> getAllConsumersWithMedia() {
        return cs.findAllConsumersWithMediaIds();
    }
    @GetMapping("/{id}")
    public Mono<Consumer> getConsumer(@PathVariable("id") Long id)
    {
        return cs.getConsumer(id);
    }


    @PutMapping
    private Mono<Consumer> updateConsumer(@RequestBody Consumer c)
    {
        return cs.updateConsumer(c);
    }
    @DeleteMapping("/{id}")
    private Mono<Void> deleteConsumer(@PathVariable("id") Long id)
    {
        return cs.deleteConsumer(id);
    }

    @PostMapping("/createRelationship/{id1}/{id2}/{rating}") //id1 =consumer_id   //id2 = media_id
    private Mono<Relationship> createRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2, @PathVariable("rating") int rating)
    {
        Relationship  r = new Relationship();
        r.setConsumerId(id1);
        r.setMediaId(id2);
        r.setRating(rating);
        return rs.createRelationship(r);
    }

    @GetMapping("/readRelationship/{id1}/{id2}")
    private Mono<Relationship> readRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        return rs.readRelationship(id1,id2);
    }
    @GetMapping("/getAllRelationships")
    private Flux<Relationship> getAllRelationships()
    {
        return rs.getAllRelationships();
    }

    @DeleteMapping("/deleteRelationship/{id1}/{id2}")
    private Mono<Void> deleteRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        return rs.deleteRelationship(id1,id2);
    }






}
