package org.example.controllers;

import org.example.models.Consumer;
import org.example.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consumers")
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
}
