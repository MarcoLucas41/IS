package org.example.services;

import org.example.entities.Media;
import org.example.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mr;

    public Mono<Media> saveMedia(Media m) {
        return mr.save(m);
    }

    public Flux<Media> getAllMedia() {
        return mr.findAll();
    }

    public Mono<Media> getMedia(Long id) {
        return mr.findById(id);
    }
    public Mono<Media> updateMedia(Media m) {
        return mr.save(m);
    }

    public Mono<Void> deleteMedia(Long id) {
        return mr.deleteById(id);
    }
}