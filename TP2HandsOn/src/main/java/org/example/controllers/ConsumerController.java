package org.example.controllers;

import org.example.entities.Consumer;
import org.example.services.ConsumerService;
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

    @PostMapping("/{id1}/{id2}") //id1 =consumer_id   //id2 = media_id
    private Mono<Consumer> createRelationship(@PathVariable("id1")Long id1, @PathVariable("id2") Long id2)
    {
        System.out.println("RELATIONSHIP: CREATE: NOW!!!");
        return cs.createRelationship(id1,id2);
    }

//    @DeleteMapping
//    private int deleteRelationship()
//    {
//        return ms.deleteRelationship();
//    }
//
//    @GetMapping
//    private Flux<Media> readRelationship()
//    {
//      return ms.getAllConsumers();
//    }
}
