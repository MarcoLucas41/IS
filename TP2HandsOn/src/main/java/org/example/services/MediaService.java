package org.example.services;

import org.example.models.Media;
import org.example.repositories.MediaRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MediaService {

    private MediaRepository mr;

    public Flux<Media> getAllMedia(){
        return mr.findAll();
    }
}
