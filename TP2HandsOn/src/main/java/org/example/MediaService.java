package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MediaService {

    private MediaRepository mr;

    public Flux<Media> getAllMedia(){
        return mr.findAll();
    }
}
