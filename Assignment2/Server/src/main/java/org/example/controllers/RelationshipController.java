package org.example.controllers;

import org.example.entities.Relationship;
import org.example.services.RelationshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/relationship")
public class RelationshipController
{
    private static final Logger logger = LoggerFactory.getLogger(RelationshipController.class);

    @Autowired
    private RelationshipService rs;
    @PostMapping("create/{id1}/{id2}/{rating}") //id1 = consumer_id   //id2 = media_id
    private Mono<Relationship> createRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2, @PathVariable("rating") int rating)
    {
        logger.info("Request to create relationship by IDS: {} and {}", id1, id2);
        Relationship  r = new Relationship();
        r.setConsumerId(id1);
        r.setMediaId(id2);
        r.setRating(rating);
        return rs.createRelationship(r);
    }

    // Read all media ids  for consumer id
    @GetMapping("/consumer/{id}")
    private Flux<Long> getAllMediasByConsumerID(@PathVariable("id")Long id)
    {
        logger.info("Request to get all medias by consumer ID: {}", id);
        return rs.getAllMediasByConsumerID(id);
    }

    // Read all consumers ids by media id
    @GetMapping("/media/{id}")
    private Flux<Long> getAllConsumersByMediaID(@PathVariable("id")Long id)
    {
        logger.info("Request to get all consumers by media ID: {}", id);
        return rs.getAllConsumersByMediaID(id);
    }

    @GetMapping("/getAllRelationships")
    private Flux<Relationship> getAllRelationships()
    {
        logger.info("Request to get all relationships");
        return rs.getAllRelationships();
    }

    @DeleteMapping("/delete/{id1}/{id2}")
    private Mono<Void> deleteRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        logger.info("Request to delete relationship by IDS: {} and {}", id1, id2);
        return rs.deleteRelationship(id1,id2);
    }
}
