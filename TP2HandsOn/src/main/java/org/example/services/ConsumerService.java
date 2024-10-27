package org.example.services;

import org.example.models.Consumer;
import org.example.models.Media;
import org.example.repositories.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConsumerService
{
    @Autowired
    private ConsumerRepository cr;
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
}
