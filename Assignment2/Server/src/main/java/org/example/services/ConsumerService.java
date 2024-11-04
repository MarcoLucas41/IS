package org.example.services;

import org.example.dtos.ConsumerWithMedia;
import org.example.entities.Consumer;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class ConsumerService
{
    @Autowired
    private ConsumerRepository cr;
    @Autowired
    private MediaRepository mr;

    private Mono<Boolean> customerExists(Long id) {
        return cr.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new IllegalArgumentException());
            } else {
                sink.next(exists);
            }
        });
    }

    public Mono<Consumer> saveConsumer(Consumer c) {
        return cr.save(c);
    }

    public Flux<Consumer> getAllConsumers() {
        return cr.findAll();
    }

    public Mono<Consumer> getConsumer(Long id) {
        return cr.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
    public Flux<ConsumerWithMedia> findAllConsumersWithMediaIds(){
        cr.findAllConsumersWithMediaIds().subscribe(System.out::println);
        return cr.findAllConsumersWithMediaIds();
    };

    public Mono<Consumer> updateConsumer(Consumer c) {
        return customerExists(c.getId()).then(cr.save(c));
    }

    public Mono<Void> deleteConsumer(Long id) {
        return cr.deleteById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
}
