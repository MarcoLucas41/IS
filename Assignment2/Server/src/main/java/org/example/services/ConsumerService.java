package org.example.services;

import org.example.dtos.ConsumerWithMedia;
import org.example.entities.Consumer;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);


    private Mono<Boolean> customerExists(Long id) {
        logger.info("Verifying if a consumer exists by ID: {}", id);
        return cr.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new IllegalArgumentException());
            } else {
                sink.next(exists);
            }
        });
    }

    public Mono<Consumer> saveConsumer(Consumer c)
    {
        logger.info("Saving the consumer: {}", c.getName());
        return cr.save(c);
    }

    public Flux<Consumer> getAllConsumers()
    {
        logger.info("Getting all Consumers.");
        return cr.findAll();
    }

    public Mono<Consumer> getConsumer(Long id) {
        logger.info("Getting a consumer by ID: {}", id);
        return cr.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
    public Flux<ConsumerWithMedia> findAllConsumersWithMediaIds(){
        logger.info("Finding all consumers with media IDs");
        cr.findAllConsumersWithMediaIds().subscribe(System.out::println);
        return cr.findAllConsumersWithMediaIds();
    };

    public Mono<Consumer> updateConsumer(Consumer c) {
        logger.info("Updating consumer: {}", c.getName());
        return customerExists(c.getId()).then(cr.save(c));
    }

    public Mono<Void> deleteConsumer(Long id) {
        logger.info("Deleting consumer by ID:{}", id);
        return cr.deleteById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
}
