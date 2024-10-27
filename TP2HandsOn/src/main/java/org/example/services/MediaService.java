package org.example.services;

import org.example.models.Consumers;
import org.example.models.Media;
import org.example.repositories.MediaRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MediaService
{

    private MediaRepository mr;
    public Flux<Media> getAllMedia()
    {
        return mr.findAll();
    }

    public int createMedia(Media m)
    {
        return 0;
    }

    public int createRelationship(Media m, Consumers c)
    {

        return 0;
    }

    public Flux<Media> getAllConsumers() {
        return null;
    }

    public int updateSpecificMedia() {
        return 0;
    }
    public int updateSpecificConsumer() {
        return 0;
    }

    public int deleteRelationship() {
        return 0;
    }

    public int deleteSpecificConsumer() {
        return 0;
    }

    public int updateMedia() {
        return 0;
    }

    public int updateConsumer() {
        return 0;
    }

    public int deleteSpecificMedia() {
        return 0;
    }
}
