package org.example.services;

import org.example.entities.Consumer;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumerService
{
    @Autowired
    private ConsumerRepository cr;
    @Autowired
    private MediaRepository mr;

    public Mono<Consumer> saveConsumer(Consumer c) {
        return cr.save(c);
    }

    public Flux<Consumer> getAllConsumers() {
        return cr.findAll();
    }

    public Mono<Consumer> getConsumer(Long id) {
        return cr.findById(id);
    }

    public Mono<Consumer> updateConsumer(Consumer c) {
        return cr.save(c);
    }

    public Mono<Void> deleteConsumer(Long id) {
        return cr.deleteById(id);
    }

    public Mono<Consumer> createRelationship(Long id1, Long id2) //id1 =consumer_id   //id2 = media_id
    {
        return cr.findById(id1).flatMap(consumer -> {
                    consumer.addMedia(id2);
                    return cr.save(consumer);
                });

    }
}
