package org.example.services;

import org.example.models.Consumers;
import org.example.models.Media;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import reactor.core.publisher.Flux;

public class ConsumerService
{
    private ConsumerRepository mr;

    public int createConsumer(Consumers c) {
        return 0;
    }

    public Flux<Media> getAllConsumers() {
        return null;
    }

    public int updateConsumer(Consumers c) {
        return 0;
    }

    public int deleteSpecificConsumer(Consumers c) {
        return 0;
    }
}
