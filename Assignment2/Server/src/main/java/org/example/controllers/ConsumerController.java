package org.example.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService cs;
    @Autowired
    private RelationshipService rs;
    @PostMapping
    private Mono<Consumer> saveConsumer(@RequestBody Consumer c)
    {
        logger.info("Request to save the consumer: {}", c.getName());
        return cs.saveConsumer(c);
    }
    @GetMapping
    private Flux<Consumer> getAllConsumers()
    {
        logger.info("Request to get all consumers");
        return cs.getAllConsumers();
    }

    @GetMapping("/{id}")
    public Mono<Consumer> getConsumer(@PathVariable("id") Long id)
    {
        logger.info("Request to get consumer by ID: {}", id);
        return cs.getConsumer(id);
    }

    @PutMapping
    private Mono<Consumer> updateConsumer(@RequestBody Consumer c)
    {
        logger.info("Request to update consumer with: {}", c.getName());
        return cs.updateConsumer(c);
    }
    @DeleteMapping("/{id}")
    private Mono<Void> deleteConsumer(@PathVariable("id") Long id)
    {
        logger.info("Request to delete consumer by ID: {}", id);
        return cs.deleteConsumer(id);
    }






    @PostMapping("/createRelationship/{id1}/{id2}/{rating}") //id1 =consumer_id   //id2 = media_id
    private Mono<Relationship> createRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2, @PathVariable("rating") int rating)
    {
        logger.info("Request to create relationship with consumer ID: {} and media ID: {}", id1, id2);
        Relationship  r = new Relationship();
        r.setConsumerId(id1);
        r.setMediaId(id2);
        r.setRating(rating);
        return rs.createRelationship(r);
    }

    @GetMapping("/readRelationship/{id1}/{id2}")
    private Mono<Relationship> readRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        logger.info("Request to read relationship with consumer ID: {} and media ID: {}", id1, id2);
        rs.readRelationship(id1,id2).subscribe(System.out::println);
        System.out.println("here");
        return rs.readRelationship(id1,id2);
    }
    @GetMapping("/getAllRelationships")
    private Flux<Relationship> getAllRelationships()
    {
        logger.info("Request to get all relationships");
        return rs.getAllRelationships();
    }

    @DeleteMapping("/deleteRelationship/{id1}/{id2}")
    private Mono<Void> deleteRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        logger.info("Request to delete relationship with consumer ID: {} and media ID: {}", id1, id2);
        return rs.deleteRelationship(id1,id2);
    }

}
