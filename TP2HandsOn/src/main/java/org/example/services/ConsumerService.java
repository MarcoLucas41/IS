package org.example.services;

import org.example.models.Consumers;
import org.example.models.Media;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ConsumerService
{
    @Autowired
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
